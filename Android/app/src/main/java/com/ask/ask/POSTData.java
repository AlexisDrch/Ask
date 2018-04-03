package com.ask.ask;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private int POST_OFFER = 3;
    private int POST_LOGIN = 4;
    private int POST_ACCEPT_OFFER = 5;
    private String mEmail = "";
    private String mPassword = "";

    private String request_id = "";
    private String provider_id = "";
    private String message = "";

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

    private void setMEmail(String email) {
        this.mEmail = email;
    }

    private void setMPassword(String password) {
        this.mPassword = password;
    }

    public int getStatus() {
        return STATUS;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void postRequest(String url, Request request, Context context, final VolleyCallback volleyCallback) {
        setStatus(POST_REQUEST);
        setUrl(url);
        setRequest(request);
        setContext(context);
        createPOST(volleyCallback);
    }

    public void postOffer(String url, Offer offer, Context context, final VolleyCallback volleyCallback) {
        setStatus(POST_OFFER);
        setUrl(url);
        setOffer(offer);
        setContext(context);
        createPOST(volleyCallback);
    }

    public void postlogin(String url, String mEmail, String mPassword, Context context, final VolleyCallback volleyCallback) {
        setStatus(POST_LOGIN);
        setUrl(url);
        setMEmail(mEmail);
        setMPassword(mPassword);
        setContext(context);
        createPOST(volleyCallback);
    }

    public void postAcceptOffer(String url, String request_id, String provider_id, String message, Context context, final VolleyCallback volleyCallback) {
        setStatus(POST_ACCEPT_OFFER);
        setUrl(url);
        setRequest_id(request_id);
        setProvider_id(provider_id);
        setMessage(message);
        setContext(context);
        createPOST(volleyCallback);
    }

    private void createPOST(final VolleyCallback volleyCallback) {
        RequestQueue postQueue = Volley.newRequestQueue(context);

        StringRequest postRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("POST response : ", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            volleyCallback.onSuccess(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", "Error: " + error.getMessage());
                        volleyCallback.onFailure();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                if (STATUS == POST_REQUEST) {
                    Log.d("POSTData", "POST_REQUEST");
                    return getRequestParams();
                } else if (STATUS == POST_OFFER) {
                    Log.d("POSTData", "POST_OFFER");
                    return getOfferParams();
                } else if (STATUS == POST_LOGIN) {
                    Log.d("POSTData", "POST_LOGIN");
                    return getLoginParams();
                } else if (STATUS == POST_ACCEPT_OFFER) {
                    Log.d("POSTData", "POST_ACCEPT_OFFER");
                    return getAcceptOfferParams();
                } else {
                    Log.d("POSTData", "NULL");
                    return null;
                }
            }
        };

        postQueue.add(postRequest);
    }

    private Map<String, String> getRequestParams() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("item_id", request.getItem_id());
        requestParams.put("requester_id", request.getRequester_id());
        requestParams.put("request_price", request.getRequest_price());
        requestParams.put("begin_date", request.getBegin_date());
        requestParams.put("end_date", request.getEnd_date());
        requestParams.put("lon", "-1"); //TODO: would ask user to set location as temporary variables
        requestParams.put("lat", "-1");
        requestParams.put("description", request.getDescription());

        return requestParams;
    }

    private Map<String, String> getOfferParams() {
        Map<String, String> offerParams = new HashMap<>();
        offerParams.put("belonging_id", offer.getBelonging_id());
        offerParams.put("request_id", "" + offer.getRequest_id());
        offerParams.put("provider_id", "" + offer.getProvider_id());
        offerParams.put("begin_date", offer.getBegin_date());
        offerParams.put("end_date", offer.getEnd_date());
        offerParams.put("lon", "-1"); //TODO: would ask user to set location as temporary variables
        offerParams.put("lat", "-1");
        offerParams.put("offer_price", offer.getOffer_price());
        offerParams.put("description", offer.getDescription());
        offerParams.put("message", offer.getMessage());

        return offerParams;
    }

    private Map<String, String> getLoginParams() {
        Map<String, String> offerParams = new HashMap<>();
        offerParams.put("email", "" + mEmail);
        offerParams.put("password", "" + mPassword);

        return offerParams;
    }

    private Map<String, String > getAcceptOfferParams() {
        Map<String, String> acceptOfferParams = new HashMap<>();
        acceptOfferParams.put("belonging_id", "-1");
        acceptOfferParams.put("request_id", "" + getRequest_id());
        acceptOfferParams.put("provider_id", "" + getProvider_id());
        acceptOfferParams.put("begin_date", "");
        acceptOfferParams.put("end_date", "");
        acceptOfferParams.put("lon", "-1");
        acceptOfferParams.put("lat", "-1");
        acceptOfferParams.put("description", "");
        acceptOfferParams.put("message", getMessage());

        return acceptOfferParams;
    }

}