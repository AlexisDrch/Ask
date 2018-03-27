package com.ask.ask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pulakazad on 3/19/18.
 */

public class VolleyFetcher extends AsyncTask<Void, Void, Void>  {

    private String url;
    private Context myContext;
    private String data;

    public VolleyFetcher(String url, Context myContext) {
        this.myContext = myContext;
        this.url = url;
    }

    /*
        Reads json stream and calls appropriate parser
     */
    public void jsonReader(final VolleyCallback volleyCallback) {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
            try {
                Log.d("GET response", response);
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                // handle json response from caller class
                volleyCallback.onSuccess(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GET error", error.getMessage());
                // Anything you want
                volleyCallback.onFailure();
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





