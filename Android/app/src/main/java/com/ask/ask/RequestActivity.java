package com.ask.ask;

import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.HashMap;

/** Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 */
public class RequestActivity extends AppCompatActivity {

    private ImageView imageViewItemImage;
    private Spinner spinnerLocalItems;
    private TextView textViewItemName;
    private TextView textViewBeginDate;
    private TextView textViewEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonDatePicker;
    private Button buttonAsk2;
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
        buttonDatePicker = (Button) findViewById(R.id.buttonDatePicker);
        buttonAsk2 = (Button) findViewById(R.id.buttonAsk2);

        // get local item data
        final HashMap<String, Item> localItems = LocalData.getHashMapItemsById();

        // prepare the local items spinner
        spinnerLocalItems.setEnabled(true);
        spinnerLocalItemsAdapter = ArrayAdapter.createFromResource(this,  R.array.localItemsArray,
                android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalItems.setAdapter(spinnerLocalItemsAdapter);

        /*
            * handle event on local items spinner
            * i.e when a local item is selected, should fill the appropriate fields
          */
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

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateBeginPickerDialog(v, "Select Begin Date");
            }
        });

        buttonAsk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textViewItemName.getText()) || TextUtils.isEmpty(textViewBeginDate.getText())
                        || TextUtils.isEmpty(textViewEndDate.getText()) || TextUtils.isEmpty(editTextPrice.getText())
                        || TextUtils.isEmpty(editTextDescription.getText())) {
                    Toast.makeText(RequestActivity.this, "All fields require an input.", Toast.LENGTH_SHORT).show();
                } else {
                    int itemImage = itemImageResource;
                    String itemName = textViewItemName.getText().toString();
                    String beginDate = textViewBeginDate.getText().toString();
                    String endDate = textViewEndDate.getText().toString();
                    double price = Double.parseDouble(editTextPrice.getText().toString());
                    String description = editTextDescription.getText().toString();

                    //for RequestConfirmationActivity display
                    Intent intent = new Intent(view.getContext(), RequestConfirmationActivity.class);
                    intent.putExtra("itemImage", itemImage);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("beginDate", beginDate);
                    intent.putExtra("endDate", endDate);
                    intent.putExtra("price", price);
                    intent.putExtra("description", description);

                    //would pass in current user info who is logged in
                    User user = LocalData.geUserRequesterInstance();

                    Item item = new Item(7, "Sleeping Bag", null,
                            7.00, null, R.mipmap.item_sleepingbag);


                    com.ask.ask.Request request = new com.ask.ask.Request("" + user.getUser_id(), "14", "" + item.getItem_id(), beginDate, endDate, description);
                    //Volley POST
                    final String url = "https://ask-capa.herokuapp.com/api/requests";
                    POSTData postData = new POSTData();
                    postData.postRequest(url, request, getApplicationContext());

                    startActivity(intent);
                }

            }
        });

    }

    /*
      * Use the item selected from LocalData to update the field
    */
    public void updateItemFields(Item currentItem){
        textViewItemName.setText(currentItem.getName());
        imageViewItemImage.setImageResource(currentItem.getIcon());
    }

    /*
      * Used to pick date.
    */
    public void showDateBeginPickerDialog(View view, String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerBeginFragment = new DatePickerFragment(textViewBeginDate, textViewEndDate, view, 0);
        datePickerBeginFragment.show(getSupportFragmentManager(), "dateBeginPicker");
    }

}