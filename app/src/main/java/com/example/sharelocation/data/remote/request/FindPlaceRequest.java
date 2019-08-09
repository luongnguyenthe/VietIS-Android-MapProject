package com.example.sharelocation.data.remote.request;

import com.example.sharelocation.data.OnRequestDataListener;
import com.example.sharelocation.data.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindPlaceRequest extends HttpGetRequest<List<Place>> {

    public FindPlaceRequest(OnRequestDataListener<List<Place>> listener) {
        super(listener);
    }

    @Override
    List<Place> parseJSONToDataClass(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
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
                places.add(new Place(0, title, address, latitude, longitude));
            }
        }
        return places;
    }
}
