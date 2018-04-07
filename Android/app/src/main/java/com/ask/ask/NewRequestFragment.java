package com.ask.ask;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Created by alexisdurocher on 28/03/2018.
 */

public class NewRequestFragment extends Fragment {

    private ImageView imageViewItemImage;
    private Spinner spinnerLocalItems;
    private TextView textViewItemName;
    private TextView textViewBeginDate;
    private TextView textViewEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button datePickerButton;
    private Button askNewRequestButton;
    private Item currentItem;
    private Context fragmentContext;

    private ArrayAdapter<CharSequence> spinnerLocalItemsAdapter;
    private int localItemsSpinnerPosition = -1;
    private int itemImageResource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContext = this.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        View rootView = inflater.inflate(R.layout.fragment_new_request, container, false);

        imageViewItemImage = (ImageView) rootView.findViewById(R.id.imageViewItemImage);
        spinnerLocalItems = (Spinner) rootView.findViewById(R.id.spinnerItems);
        //textViewItemName = (TextView) rootView.findViewById(R.id.textViewItemName);
        textViewBeginDate = (TextView) rootView.findViewById(R.id.textViewBeginDate);
        textViewEndDate = (TextView) rootView.findViewById(R.id.textViewEndDate);
        editTextPrice = (EditText) rootView.findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) rootView.findViewById(R.id.editTextDescription);
        datePickerButton = (Button) rootView.findViewById(R.id.buttonDatePicker);
        askNewRequestButton = (Button) rootView.findViewById(R.id.buttonAsk2);

        // prepare the local items spinner
        spinnerLocalItems.setEnabled(true);
        // spinner colors
        spinnerLocalItems.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        spinnerLocalItems.setBackgroundColor(getResources().getColor(R.color.primaryGreen));

        spinnerLocalItemsAdapter = ArrayAdapter.createFromResource(fragmentContext,  R.array.localItemsArray,
                android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalItems.setAdapter(spinnerLocalItemsAdapter);


        //handle event on local items spinner: i.e when a local item is selected, should fill the appropriate fields
        spinnerLocalItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentItem = (Item) LocalData.getHashMapItemsByName().get(spinnerLocalItemsAdapter.getItem(position));
                updateItemFields(currentItem);
                localItemsSpinnerPosition = position;
                view.setBackgroundColor(getResources().getColor(R.color.primaryGreen));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateBeginPickerDialog(v, "Select Begin Date");
            }
        });

        //changing button font
        Typeface font = Typer.set(fragmentContext).getFont(Font.ROBOTO_THIN);
        askNewRequestButton.setTypeface(font);


        askNewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // toast message if a field is not set
                if ( TextUtils.isEmpty(textViewBeginDate.getText())
                        || TextUtils.isEmpty(textViewEndDate.getText()) || TextUtils.isEmpty(editTextDescription.getText()))
                    Toast.makeText(fragmentContext, "All fields require an input.", Toast.LENGTH_SHORT).show();
                else {
                    // create new request object
                    String beginDate = textViewBeginDate.getText().toString();
                    String endDate = textViewEndDate.getText().toString();
                    String description = editTextDescription.getText().toString();
                    String request_price = editTextPrice.getText().toString();

                    // create corresponding request object
                    final Request newRequest = new Request(
                            ""+LocalData.getCurrentUserInstance().getUser_id(),
                            ""+-1,
                            ""+request_price,
                            ""+currentItem.getItem_id(),
                            ""+beginDate,
                            ""+endDate, description);

                    //post new request json object
                    final String url = "https://ask-capa.herokuapp.com/api/requests";
                    POSTData postData = new POSTData();
                    postData.postRequest(url, newRequest, fragmentContext, new VolleyCallback() {
                        @Override
                        public void onSuccess(JSONArray jsonArray) {
                            //pass request object for RequestConfirmationActivity display
                            Intent intent = new Intent(fragmentContext, RequestConfirmationActivity.class);
                            intent.putExtra("Request", (Serializable) newRequest);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure() {
                            Log.d("NewRequestFragment", "failure posting request!");
                            // handle failure on posting request
                            Log.d("ERRORPOST", newRequest.toString());
                        }
                    });
                }
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;

    }

    /*
      * Update the layout with selected item
    */
    public void updateItemFields(Item currentItem){
        //textViewItemName.setText(currentItem.getName());
        imageViewItemImage.setImageResource(currentItem.getIcon());
        editTextPrice.setText(""+currentItem.getPrice() +"0");
        // set a lambda description for the request
        String description = "Hey !\n" +
                "I am looking for a " + currentItem.getName() + ".\n" +
                "I would be glad to hear from you, thanks !\n" + LocalData.getCurrentUserInstance().getName();
        editTextDescription.setText(description);
    }

    /*
      * pick data in calendar.
    */
    public void showDateBeginPickerDialog(View view, String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerBeginFragment = new DataPickerFragment(textViewBeginDate, textViewEndDate, view, 0);
        datePickerBeginFragment.show(getFragmentManager(), "dateBeginPicker");
    }

}




