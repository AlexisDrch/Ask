package com.ask.ask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 */

public class RequestActivity extends AppCompatActivity {

    private ImageView imageViewItemImage;
    private Button buttonLoadImage;
    private SearchView searchViewItemSearch;
    private EditText edtTextItemName;
    private EditText editTextBeginDate;
    private EditText editTextEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonAsk2;
    private Button view_all;

    private static int RESULT_LOAD_IMAGE = 1;
    public static final int GET_FROM_GALLERY = 1;

    //generic item id counter
    private int item_count = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //Creating the user
        //TODO: should be able to call the user from the MainActivity
        final User request_user = new User(6, "Jim", 29, R.drawable.jim_profile,
                "770-783-2923", "606-3727 Ullamcorper. Street " +
                "Roseville, NH 11523");



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ask-capa.herokuapp.com/api/requests/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestService service = retrofit.create(RequestService.class);

        imageViewItemImage = (ImageView) findViewById(R.id.imageViewItemImage);
        buttonLoadImage = findViewById(R.id.buttonLoadImage);
        edtTextItemName = findViewById(R.id.editTextItemName);
        editTextBeginDate = findViewById(R.id.editTextBeginDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAsk2 = findViewById(R.id.buttonAsk2);
        view_all = findViewById(R.id.view_all);

        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
//                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, RESULT_LOAD_IMAGE);

//                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
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
                if (TextUtils.isEmpty(edtTextItemName.getText()) || TextUtils.isEmpty(editTextBeginDate.getText())
                        || TextUtils.isEmpty(editTextEndDate.getText()) || TextUtils.isEmpty(editTextPrice.getText())
                        || TextUtils.isEmpty(editTextDescription.getText())) {
                    Toast.makeText(RequestActivity.this, "All fields require an input.", Toast.LENGTH_SHORT).show();
                } else {
                    String itemName = edtTextItemName.getText().toString();
                    String beginDate = editTextBeginDate.getText().toString();;
                    String endDate = editTextEndDate.getText().toString();;
                    double price = Double.parseDouble(editTextPrice.getText().toString());
                    String description = editTextDescription.getText().toString();

                    //should probably utilize parceable
                    Intent intent = new Intent(view.getContext(), RequestConfirmationActivity.class);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("beginDate", beginDate);
                    intent.putExtra("endDate", endDate);
                    intent.putExtra("price", price);
                    intent.putExtra("description", description);
//                intent.putExtra("itemImage", imageViewItemImage.getImageMatrix());




                    Item newItem = new Item(item_count, itemName, null, price, null, 0);
                    item_count++;


//
//                    //call to database
//                    Request request = new Request(request_user, newItem, beginDate, endDate, description);
//                    Call<Request> createCall = service.create(request);
//
//                    createCall.enqueue(new Callback<Request>() {
//                        @Override
//                        public void onResponse(Call<Request> foo, Response<Request> resp) {
//                            String newRequest = resp.headers().toString();
//
//                            System.out.println("Headers: " + newRequest);
//
////
////                            int duration = Toast.LENGTH_SHORT;
////                            if (newRequest.getItem() != null) {
////                                Toast toast = Toast.makeText(getBaseContext(), "Added new request to database" + newRequest.getItem().getName(), duration);
////                                toast.show();
////                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Request> foo, Throwable t) {
//                            t.printStackTrace();
//
//                            int durationShort = Toast.LENGTH_SHORT;
//                            Toast toast = Toast.makeText(getBaseContext(), t.getMessage(), durationShort);
//                            toast.show();
//
//                        }
//                    });

                    //create request for user

                    startActivity(intent);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            Log.d("RequestActivity", "setting image");
//            imageViewItemImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//            //TODO: not setting image in imageView correctly
//            Log.d("RequestActivity", "after");
//        }

//        //Detects request codes
//        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
//            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage); //e
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }

    }

}