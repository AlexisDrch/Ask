package com.ask.ask;

import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.METHOD;

/** Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 */

public class RequestActivity extends AppCompatActivity {

    private ImageView imageViewItemImage;
    private Button buttonLoadImage;
    private Button buttonDatePicker;
    private Spinner spinnerCategories;
    private Spinner spinnerItems;
    private TextView textViewItemName;
    private TextView textViewBeginDate;
    private TextView textViewEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonAsk2;
    private Button view_all;

    private ArrayAdapter<CharSequence> categoriesSpinnerAdapter;
    private int categoriesSpinnerPosition = -1;
    private ArrayAdapter<CharSequence> itemsSpinnerAdapter;
    private int itemsSpinnerPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        view_all = (Button) findViewById(R.id.view_all);

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


        //---------- Spinner
        spinnerCategories.setEnabled(true);

        categoriesSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categoriesArray, android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesSpinnerAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("spinner", "in");
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
                        Log.d("spinner", "else");
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
                        textViewItemName.setText(campingItemsArray[position]);
                        break;
                    case 1:
                        String[] beachItemsArray = getResources().getStringArray(R.array.itemsBeachArray);
                        textViewItemName.setText(beachItemsArray[position]);
                        break;
                    case 2:
                        String[] itemsHikingArray = getResources().getStringArray(R.array.itemsHikingArray);
                        textViewItemName.setText(itemsHikingArray[position]);
                        break;
                    default:

                }

                itemsSpinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //----------

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: date validation
                //TODO: set end date to be day after the begin date to make it more concise
                //TODO: merge into 1 calendar so user can see both dates at same time
                showDateBeginPickerDialog(v);
            }
        });

        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewAllRequestsActivity.class);
                startActivity(intent);
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
                    String itemName = textViewItemName.getText().toString();
                    String beginDate = textViewBeginDate.getText().toString();
                    String endDate = textViewEndDate.getText().toString();
                    double price = Double.parseDouble(editTextPrice.getText().toString());
                    String description = editTextDescription.getText().toString();

                    Intent intent = new Intent(view.getContext(), RequestConfirmationActivity.class);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("beginDate", beginDate);
                    intent.putExtra("endDate", endDate);
                    intent.putExtra("price", price);
                    intent.putExtra("description", description);
//                intent.putExtra("itemImage", imageViewItemImage.getImageMatrix());


//                    User user = new User("1", "Mark Sanders", 51, -1, "+33676564537", " 4 rue de Saint-tout,  Metz, France.");
//                    Item item = new Item("1", "1", itemName, null, price, null, -1, user);
//                    Request request = new Request(user, item, beginDate, endDate, description);

//                    sendData(request);

//                    Log.d("POST", "1");
//                    sendDataToServer(request);
//                    Log.d("POST", "10");

                    startActivity(intent);
                }

            }
        });

    }

    /*
    * Used to pick date.
     */
    public void showDateBeginPickerDialog(View v) {
        Toast toast = Toast.makeText(getApplicationContext(), "Select Begin Date", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerBeginFragment = new DatePickerFragment(textViewBeginDate, textViewEndDate, v, 0);
        datePickerBeginFragment.show(getSupportFragmentManager(), "dateBeginPicker");
    }

    //----------POST

//    private void sendData() {
//        String url = "http://httpbin.org/post";
//
//        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        }) {
//
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//
//                Map<String, String> postParam = new HashMap<String, String>();
//
//                postParam.put("email", "asd@asd.com");
//                postParam.put("password", "asd");
//
//
//                return postParam;
//            }
//
//        };
//
//        requestQueue.add(jsonObjRequest);
//
//
//
//    }
//
//
//
//
//
//
//
//
//    private void sendDataToServer(Request request) {
//        final JSONObject requestJSON = formatJSONRequest(request);
//        Log.d("POST", "4");
//
//        if (requestJSON != null) {
//            new AsyncTask<Void, Void, String>() {
//
//                @Override
//                protected String doInBackground(Void... params) {
//                    Log.d("POST", "5");
//                    return getServerResponse(requestJSON);
//                }
//
//                @Override
//                protected void onPostExecute(String result) {
//                    Log.d("POST", "6");
//                    Log.d("requestPOSTResult", "" + result);
//                }
//
//            }.execute();
//        } else {
//            Log.d("POST", "7");
//            Toast.makeText(this, "Data NOT Sent!", Toast.LENGTH_SHORT);
//        }
//
//    }
//
//    private JSONObject formatJSONRequest(Request request) {
//        final JSONObject requestJSON = new JSONObject();
//        try {
//            Log.d("POST", "2");
//            requestJSON.put("item_id", request.getItem().getItem_id());
//            requestJSON.put("requester_id", request.getRequester().getItem_id());
//            requestJSON.put("begin_date", request.getBegin_date());
//            requestJSON.put("end_date", request.getEnd_date());
////            requestJSON.put("lon", );
////            requestJSON.put("lan", );
//            requestJSON.put("description", request.getDescription());
//
//            Log.d("POST", "3a");
//            return requestJSON;
//        } catch (JSONException e) {
//            Log.d("POST", "3b");
//        }
//
//        return null;
//    }
//
//    private String getServerResponse(JSONObject requestJSON) {
//        Log.d("POST", "8");
//
//        DataOutputStream outputStream = null;
//
//        try {
//            URL url = new URL("https://ask-capa.herokuapp.com/api/requests");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//            urlConnection.setRequestProperty("Accept", "application/json");
//            urlConnection.setDoOutput(true);
//            urlConnection.setDoInput(true);
//            urlConnection.connect();
//
//            Log.d("POST", "81");
//            outputStream = new DataOutputStream(urlConnection.getOutputStream());
//            outputStream.writeChars("request=" + requestJSON.toString());
//            outputStream.flush();
//            outputStream.close();
////            urlConnection.disconnect();
//            Log.d("POST", "82");
////            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//            Log.d("POST", "9a");
//
//            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                Log.d("Success","Success");
//            } else {
//                Log.d("Failure", "Failure");
//            }
//
//        } catch (MalformedURLException e) {
//            Log.d("POST", "9b");
//            e.printStackTrace();
//        } catch (IOException e) {
//            Log.d("POST", "9c");
//            e.printStackTrace();
//        } catch (Exception e) {
//            Log.d("POST", "9d");
//            e.printStackTrace();
//        }
//
//        Log.d("POST", "9e");
//
//        return null;
//    }
//
//    //----------
//
}