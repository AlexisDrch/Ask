package com.ask.ask;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;

/** Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 */
public class NewRequestActivity extends AppCompatActivity {

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

    private ArrayAdapter<CharSequence> spinnerLocalItemsAdapter;
    private int localItemsSpinnerPosition = -1;
    private int itemImageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        imageViewItemImage = (ImageView) findViewById(R.id.imageViewItemImage);
        spinnerLocalItems = (Spinner) findViewById(R.id.spinnerItems);
        textViewItemName = (TextView) findViewById(R.id.textViewItemName);
        textViewBeginDate = (TextView) findViewById(R.id.textViewBeginDate);
        textViewEndDate = (TextView) findViewById(R.id.textViewEndDate);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        datePickerButton = (Button) findViewById(R.id.buttonDatePicker);
        askNewRequestButton = (Button) findViewById(R.id.buttonAsk2);

        // prepare the local items spinner
        spinnerLocalItems.setEnabled(true);
        spinnerLocalItemsAdapter = ArrayAdapter.createFromResource(this,  R.array.localItemsArray,
                android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalItems.setAdapter(spinnerLocalItemsAdapter);


        //handle event on local items spinner: i.e when a local item is selected, should fill the appropriate fields
        spinnerLocalItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentItem = (Item) LocalData.getHashMapItemsById().values().toArray()[position];
                updateItemFields(currentItem);
                localItemsSpinnerPosition = position;
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


        askNewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // toast message if a field is not set
            if (TextUtils.isEmpty(textViewItemName.getText()) || TextUtils.isEmpty(textViewBeginDate.getText())
                    || TextUtils.isEmpty(textViewEndDate.getText()) || TextUtils.isEmpty(editTextDescription.getText())) {
                Toast.makeText(NewRequestActivity.this, "All fields require an input.", Toast.LENGTH_SHORT).show();
            } else {
                // create new request object
                String itemName = textViewItemName.getText().toString();
                String beginDate = textViewBeginDate.getText().toString();
                String endDate = textViewEndDate.getText().toString();
                //double price = Double.parseDouble(editTextPrice.getText().toString()); @todo add request price in database
                String description = editTextDescription.getText().toString();

                // create corresponding request object
                Request newRequest = new Request(
                        ""+LocalData.geUserRequesterInstance().getUser_id(), ""+-1,
                        ""+currentItem.getItem_id(),""+beginDate,
                        ""+endDate, description);

                //post new request json object
                final String url = "https://ask-capa.herokuapp.com/api/requests";
                POSTData postData = new POSTData();
                postData.postRequest(url, newRequest, getApplicationContext());

                //pass request oject for RequestConfirmationActivity display
                Intent intent = new Intent(view.getContext(), RequestConfirmationActivity.class);
                intent.putExtra("Request", (Serializable) newRequest);
                startActivity(intent);
            }
            }
        });

    }

    /*
      * Update the layout with selected item
    */
    public void updateItemFields(Item currentItem){
        textViewItemName.setText(currentItem.getName());
        imageViewItemImage.setImageResource(currentItem.getIcon());
        editTextPrice.setText(""+currentItem.getPrice());

        // set a lambda description for the request
        String description = "Hey !\n" +
                "I am looking for a " + currentItem.getName() + ".\n" +
                "Would be glad to hear from you, thanks !\n" + LocalData.geCurrentUserInstance().getName();
        editTextDescription.setText(description);
    }

    /*
      * pick Data in calendar.
    */
    public void showDateBeginPickerDialog(View view, String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerBeginFragment = new DatePickerFragment(textViewBeginDate, textViewEndDate, view, 0);
        datePickerBeginFragment.show(getSupportFragmentManager(), "dateBeginPicker");
    }

}