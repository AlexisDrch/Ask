package com.ask.ask;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexander on 3/21/2018.
 *
 * Uses Volley POST to send data to database.
 */

public class POSTData {

    private String url;
    private Request request;
    private Offer offer;
    private Context context;

    private int STATUS;
    private int BEGIN = 0;
    private int COMPLETE = 1;
    private int POST_REQUEST = 2;
    private int POST_MATCH = 3;

    public POSTData() {
        STATUS = BEGIN;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void setStatus(int status) {
        this.STATUS = status;
    }

    public int getStatus() {
        return STATUS;
    }

    public void postRequest(String url, Request request, Context context) {
        setStatus(POST_REQUEST);
        setUrl(url);
        setRequest(request);
        setContext(context);
        createPOST();
    }

    public void postOffer(String url, Offer offer) {
        setStatus(POST_MATCH);
        setUrl(url);
        setOffer(offer);
        setContext(context);
        createPOST();
    }

    private void createPOST() {
        RequestQueue postQueue = Volley.newRequestQueue(context);

        StringRequest postRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                if (STATUS == POST_REQUEST) {
                    return getRequestParams();
                } else if (STATUS == POST_MATCH) {
                    return getOfferParams();
                } else {
                    return null; //TODO: error checking
                }
            }
        };

        postQueue.add(postRequest);
        setStatus(COMPLETE);
    }


    private Map<String, String> getRequestParams() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("item_id", request.getItem().getUuid());
        requestParams.put("requester_id", request.getRequester().getUuid());
        requestParams.put("begin_date", request.getBeginDate());
        requestParams.put("end_date", request.getEndDate());
        requestParams.put("lon", "-1"); //TODO: add this feature later
        requestParams.put("lat", "-1");
        requestParams.put("description", request.getDescription());

        return requestParams;
    }

    private Map<String, String> getOfferParams() {
        Map<String, String> offerParams = new HashMap<>();


        return offerParams;
    }

}