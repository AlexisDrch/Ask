package com.ask.ask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

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

    private String url;
    private Context myContext;
    private String data;

    public FetchRequests(String url, Context myContext) {
        this.myContext = myContext;
        this.url = url;
    }

    /*
        Reads json stream and calls appropriate parser
     */
    public void requestJsonReader(final RequestsCallback requestsCallback) {


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
            try {
                Log.d("ON RESPONSE", "Inside on response");
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                // handle json response
                requestsCallback.onSuccess(jsonArray);
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
    protected Void doInBackground(Void... voids) {
        return null;
    }
}





