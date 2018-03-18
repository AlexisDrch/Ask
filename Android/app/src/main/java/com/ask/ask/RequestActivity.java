package com.ask.ask;

import android.support.v4.app.DialogFragment;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/** Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 */

public class RequestActivity extends AppCompatActivity {

    private ImageView imageViewItemImage;
    private Button buttonLoadImage;
    private SearchView searchViewItemSearch;
    private EditText edtTextItemName;
    private TextView textViewBeginDate;
    private TextView textViewEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonDatePicker;
    private Button buttonAsk2;

//    private static int RESULT_LOAD_IMAGE = 1;
//    public static final int GET_FROM_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        imageViewItemImage = (ImageView) findViewById(R.id.imageViewItemImage);
        buttonLoadImage = (Button) findViewById(R.id.buttonLoadImage);
        edtTextItemName = (EditText) findViewById(R.id.editTextItemName);
        textViewBeginDate = (TextView) findViewById(R.id.textViewBeginDate);
        textViewEndDate = (TextView) findViewById(R.id.textViewEndDate);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        buttonDatePicker = (Button) findViewById(R.id.buttonDatePicker);
        buttonAsk2 = (Button) findViewById(R.id.buttonAsk2);

//        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, RESULT_LOAD_IMAGE);
//
//                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//            }
//        });

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: date validation, dates are backwards not sure why, figure out
                Toast.makeText(getApplicationContext(), "Select Begin Date", Toast.LENGTH_LONG).show();
                showDateBeginPickerDialog(v);
                Toast.makeText(getApplicationContext(), "Select End Date", Toast.LENGTH_LONG).show();
                showDateEndPickerDialog(v);
            }
        });

        buttonAsk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtTextItemName.getText()) || TextUtils.isEmpty(textViewBeginDate.getText())
                        || TextUtils.isEmpty(textViewEndDate.getText()) || TextUtils.isEmpty(editTextPrice.getText())
                        || TextUtils.isEmpty(editTextDescription.getText())) {
                    Toast.makeText(RequestActivity.this, "All fields require an input.", Toast.LENGTH_SHORT).show();
                } else {
                    String itemName = edtTextItemName.getText().toString();
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

                    //call to database
                    //create request for user

                    startActivity(intent);
                }

            }
        });

    }

    /*
    * Used to pick date.
     */
    public void showDateBeginPickerDialog(View v) {
        DialogFragment datePickerFragment = new DatePickerFragment(textViewBeginDate);
        datePickerFragment.show(getSupportFragmentManager(), "dateBeginPicker");
    }

    public void showDateEndPickerDialog(View v) {
        DialogFragment datePickerFragment = new DatePickerFragment(textViewEndDate);
        datePickerFragment.show(getSupportFragmentManager(), "dateEndPicker");
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
//
//    }

}