package com.ask.ask;

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

/**
 * Created by pulakazad on 2/28/18.
 *
 * Request activity to collect information for each user request.
 *
 */

public class RequestActivity extends AppCompatActivity {

    private ImageView imageViewItemImage;
    private Button buttonLoadImage;
    private SearchView searchViewItemSearch;
    private EditText editTextItemName;
    private EditText editTextBeginDate;
    private EditText editTextEndDate;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonAsk2;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        imageViewItemImage = (ImageView) findViewById(R.id.imageViewItemImage);
        buttonLoadImage = findViewById(R.id.buttonLoadImage);
        editTextItemName = findViewById(R.id.editTextItemName);
        editTextBeginDate = findViewById(R.id.editTextBeginDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAsk2 = findViewById(R.id.buttonAsk2);

        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        buttonAsk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextItemName.getText()) || TextUtils.isEmpty(editTextBeginDate.getText())
                        || TextUtils.isEmpty(editTextEndDate.getText()) || TextUtils.isEmpty(editTextPrice.getText())
                        || TextUtils.isEmpty(editTextDescription.getText())) {
                    Toast.makeText(RequestActivity.this, "You must fill in all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    String itemName = editTextItemName.getText().toString();
                    String beginDate = editTextBeginDate.getText().toString();;
                    String endDate = editTextEndDate.getText().toString();;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.d("RequestActivity", "setting image");
            imageViewItemImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //TODO: not setting image in imageView correctly
            Log.d("RequestActivity", "after");
        }

    }

}