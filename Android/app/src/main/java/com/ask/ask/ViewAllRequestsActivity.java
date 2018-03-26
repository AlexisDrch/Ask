package com.ask.ask;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class ViewAllRequestsActivity extends AppCompatActivity {

    Button click;
    public static TextView data;
    public static HashMap<String, Request> requestHashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_requests);

        click = (Button) findViewById(R.id.buttonViewAll);


        data = (TextView) findViewById(R.id.viewAllText);
        final Context myContext = this.getApplicationContext();

        click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                FetchRequests process = new FetchRequests("https://ask-capa.herokuapp.com/api/requests", myContext);
                process.requestJsonReader(new RequestsCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArrayRequests) {
                        // handle JSONOBJECT response
                        requestHashMap = JsonParser.JsonArrayRequestsToHashMapRequests(jsonArrayRequests);
                        for (String each : requestHashMap.keySet()) {
                            Log.d("KEY", each);
                        }
                    }
                });
            }
        });
    }
}


class JsonParser {

    /*
        Parse a JSON array of requests into a HashMap
        - HashMap are key / value with request_id as key
    */
    public static HashMap<String, Request> JsonArrayRequestsToHashMapRequests(JSONArray jsonArrayRequests){

        HashMap<String, Request> requestHashMap = new HashMap<>();

        for (int i = 0; i < jsonArrayRequests.length(); i++) {
            JSONObject jo = null;
            try {
                jo = jsonArrayRequests.getJSONObject(i);
                Gson gson = new Gson();
                Request newRequest = gson.fromJson(jo.toString(), Request.class);
                requestHashMap.put(newRequest.getRequest_id()+"", newRequest);

                Log.d("#REQUEST", newRequest.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return requestHashMap;
    };

}