package com.ask.ask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pulakazad on 3/19/18.
 */

public class FetchRequests extends AsyncTask<Void, Void, Void>  {

    public static final HashMap<String, Request> requestHashMap = new HashMap<>();

    private String url;
    private Context myContext;
    private String data;

    public FetchRequests(String url, Context myContext) {
        this.myContext = myContext;
        this.url = url;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("background", "background has started");

        jsonReader();
        return null;
    }


    private void jsonReader() {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.d("ON RESPONSE", "Inside on response");
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

//                    HashMap<String, Request> requestHashMap = new HashMap<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);

                        Gson gson = new Gson();
                        Request singleRequest = gson.fromJson(jo.toString(), Request.class);
                        requestHashMap.put(singleRequest.getRequest_id()+"", singleRequest);
                        Log.d("SINGLE REQUEST", singleRequest.toString());
                        Log.d("HASHMAP SIZE", requestHashMap.size()+"");
                    }
                    for (String each : requestHashMap.keySet()) {
                        Log.d("KEY", each);
                    }

                    Log.d("HASHMAP", requestHashMap.get("2").getDescription());


//                    parseRequests(requests);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NOT WORKING", "Called an Error");
                // Anything you want
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(myContext);
        requestQueue.add(stringRequest);

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("postExecute", "postExecute has started");

        jsonReader();

        if (requestHashMap.isEmpty()) {
            Log.d("EMPTY", "hashmap is empty");

            for (String each : requestHashMap.keySet()) {
                Log.d("KEY", each);
            }
        }


        ViewAllRequestsActivity.data.setText("hello");
    }

    public HashMap<String, Request> getRequestHashMap() {
        return requestHashMap;
    }

}

