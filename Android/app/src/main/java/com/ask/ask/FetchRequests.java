package com.ask.ask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pulakazad on 3/19/18.
 */

public class FetchRequests extends AsyncTask<Void, Void, Void> {

    String data = "";
    String dataParse = "";
    String singleParsed = "";

    private static final String TAG_REQUEST_ID = "request_id";
    private static final String TAG_ITEM_ID = "item_id";
    private static final String TAG_REQUESTER_ID = "requester_id";
    private static final String TAG_PROVIDER_ID = "provider_id";
    private static final String TAG_BEGIN_DATE = "begin_date";
    private static final String TAG_END_DATE = "end_date";
    private static final String TAG_LON = "lon";
    private static final String TAG_LAT = "lat";
    private static final String TAG_DESCRIPTION = "description";






    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://ask-capa.herokuapp.com/api/requests");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";


            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JsonObject = new JSONObject(data);

            JSONArray jsonData = JsonObject.getJSONArray("data");
            for (int i = 0; i < jsonData.length(); i++) {

                JSONObject JO = (JSONObject) jsonData.get(i);
                String request_id = JO.getString(TAG_REQUEST_ID);
                String item_id = JO.getString(TAG_ITEM_ID);
                String requester_id = JO.getString(TAG_REQUESTER_ID);
                String provider_id = JO.getString(TAG_PROVIDER_ID);
                String begin_date = JO.getString(TAG_BEGIN_DATE);
                String end_date = JO.getString(TAG_END_DATE);
                String lon = JO.getString(TAG_LON);
                String lat = JO.getString(TAG_LAT);
                String description = JO.getString(TAG_DESCRIPTION);


                singleParsed = "request_id: " + request_id + "\n" + "item_id: " + item_id + "\n"
                        + "requester_id: " + requester_id + "\n" +
                        "provider_id: " + provider_id + "\n" + "begin_date: " + begin_date + "\n" +
                        "end_date: " + end_date + "\n" + "lon: " + lon + "\n" + "lat: " + lat + "\n" +
                        "description: " + description + "\n";

                dataParse = dataParse + "\n" + singleParsed;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        ViewAllRequestsActivity.data.setText(this.dataParse);
    }


}
