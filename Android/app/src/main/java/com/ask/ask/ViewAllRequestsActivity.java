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

                VolleyFetcher process = new VolleyFetcher("https://ask-capa.herokuapp.com/api/requests", myContext);
                process.jsonReader(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArrayRequests) {
                        // handle JSONOBJECT response
                        requestHashMap = JsonParser.JsonArrayRequestsToHashMapRequests(jsonArrayRequests);
                        for (String each : requestHashMap.keySet()) {
                            Log.d("KEY", each);
                        }
                    }

                    @Override
                    public void onFailure() {
                        // in case
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

    /*
        Parse a JSON array of offers into a HashMap
        - HashMap are key / value with offer_id as key
    */
    public static HashMap<String, Offer> JsonArrayOffersToHashMapOffers(JSONArray jsonArrayOffers){

        HashMap<String, Offer> offerHashMap = new HashMap<>();

        for (int i = 0; i < jsonArrayOffers.length(); i++) {
            JSONObject jo = null;
            try {
                jo = jsonArrayOffers.getJSONObject(i);
                Gson gson = new Gson();
                Offer newOffer = gson.fromJson(jo.toString(), Offer.class);
                offerHashMap.put("" + newOffer.getOffer_id(), newOffer);

                Log.d("#OFFER", newOffer.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return offerHashMap;
    }

    /*
        Parse a JSON array of Users into a HashMap
        - HashMap are key / value with offer_id as key
    */
    public static HashMap<String, User> JsonArrayUsersToHashMapUsers(JSONArray jsonArrayUsers){

        HashMap<String, User> offerHashMap = new HashMap<>();

        for (int i = 0; i < jsonArrayUsers.length(); i++) {
            JSONObject jo = null;
            try {
                jo = jsonArrayUsers.getJSONObject(i);
                Gson gson = new Gson();
                User newUser = gson.fromJson(jo.toString(), User.class);
                offerHashMap.put("" + newUser.getUser_id(), newUser);

                Log.d("#USER", newUser.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return offerHashMap;
    }

}