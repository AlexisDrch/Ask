package com.ask.ask;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONArray jsonArray);
    void onFailure();
}
