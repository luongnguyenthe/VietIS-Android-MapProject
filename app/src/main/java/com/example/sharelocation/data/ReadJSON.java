package com.example.sharelocation.data;

import android.os.AsyncTask;

import com.example.sharelocation.data.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadJSON extends AsyncTask<String, Void, String> {
    private OnRequestDataListener<List<Place>> mRequestDataListener;

    public ReadJSON(OnRequestDataListener<List<Place>> requestDataListener) {
        mRequestDataListener = requestDataListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray("results");
            List<Place> places = new ArrayList<>();
            for (int i = 0; i < result.length(); i++) {
                JSONObject place = result.getJSONObject(i);
                String title = place.getString("title");
                String address = place.getString("vicinity");
                address = address.replaceAll("<br/>", " ");
                JSONArray position = place.getJSONArray("position");
                for (int j = 0; j < position.length(); j++) {
                    Double latitude = position.getDouble(0);
                    Double longitude = position.getDouble(1);
                    places.add(new Place(title, address, latitude, longitude));
                }
            }

            mRequestDataListener.onRequestDataSuccess(places);
        } catch (JSONException e) {
            mRequestDataListener.onRequestDataFailure();
        }
    }
}

