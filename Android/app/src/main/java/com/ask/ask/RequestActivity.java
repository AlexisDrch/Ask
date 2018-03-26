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

/** Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 */
public class RequestActivity extends AppCompatActivity {

    private ImageView imageViewItemImage;
    private Button buttonLoadImage;
    private Spinner spinnerCategories;
    private Spinner spinnerItems;
    private TextView textViewItemName;
    private TextView textViewBeginDate;
    private TextView textViewEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonDatePicker;
    private Button buttonAsk2;

    private ArrayAdapter<CharSequence> categoriesSpinnerAdapter;
    private int categoriesSpinnerPosition = -1;
    private ArrayAdapter<CharSequence> itemsSpinnerAdapter;
    private int itemsSpinnerPosition = -1;

    private int itemImageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        imageViewItemImage = (ImageView) findViewById(R.id.imageViewItemImage);
//        buttonLoadImage = (Button) findViewById(R.id.buttonLoadImage);
        spinnerCategories = (Spinner) findViewById(R.id.spinnerCategories);
        spinnerItems = (Spinner) findViewById(R.id.spinnerItems);
        textViewItemName = (TextView) findViewById(R.id.textViewItemName);
        textViewBeginDate = (TextView) findViewById(R.id.textViewBeginDate);
        textViewEndDate = (TextView) findViewById(R.id.textViewEndDate);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        buttonDatePicker = (Button) findViewById(R.id.buttonDatePicker);
        buttonAsk2 = (Button) findViewById(R.id.buttonAsk2);

//        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
////                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                startActivityForResult(i, RESULT_LOAD_IMAGE);
////
////                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//            }
//        });


        //Spinners ----------
        spinnerCategories.setEnabled(true);

        categoriesSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categoriesArray, android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesSpinnerAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Log.d("spinner", "camping");
                        itemsSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.itemsCampingArray, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 1:
                        Log.d("spinner", "beach");
                        itemsSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.itemsBeachArray, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 2:
                        Log.d("spinner", "hiking");
                        itemsSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.itemsHikingArray, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    default:
                        Log.d("spinner", "default");
                        return;
                }

                categoriesSpinnerPosition = position;

                itemsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerItems.setAdapter(itemsSpinnerAdapter);
                spinnerItems.setEnabled(true);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerItems.setEnabled(false);
            }
        });

        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO: automate this for changing number of categories

                switch (categoriesSpinnerPosition) {
                    case 0:
                        String[] campingItemsArray = getResources().getStringArray(R.array.itemsCampingArray);
                        Log.d("spinner", "camping - " + position);
                        textViewItemName.setText(campingItemsArray[position]);
                        String imageStr = campingItemsArray[position].toLowerCase(); //lower case
                        imageStr = imageStr.replaceAll("\\s", ""); //remove all whitespace
                        itemImageResource = getResources().getIdentifier(getPackageName() + ":drawable/" + imageStr, null, null);
                        imageViewItemImage.setImageResource(itemImageResource);
                        break;
                    case 1:
                        String[] beachItemsArray = getResources().getStringArray(R.array.itemsBeachArray);
                        Log.d("spinner", "beach - " + position + " - " + beachItemsArray[position].toLowerCase());
                        textViewItemName.setText(beachItemsArray[position]);

                        itemImageResource = R.drawable.ic_profile;
                        imageViewItemImage.setImageResource(itemImageResource);
                        break;
                    case 2:
                        String[] itemsHikingArray = getResources().getStringArray(R.array.itemsHikingArray);
                        Log.d("spinner", "beach - " + position + " - " + itemsHikingArray[position].toLowerCase());
                        textViewItemName.setText(itemsHikingArray[position]);

                        itemImageResource = R.drawable.ic_home;
                        imageViewItemImage.setImageResource(itemImageResource);
                        break;
                    default:
                        Log.d("spinner", "default");
                }

                itemsSpinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //----------

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
                    User user = new User("3", "alexanderthe1st", 19, -1, "+123456789", " 4 rue de Saint-tout,  Metz, France.");

                    Item item = new Item("1", "1", itemName, null, price, null, itemImageResource, user);
                    com.ask.ask.Request request = new com.ask.ask.Request(user, item, beginDate, endDate, description);

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