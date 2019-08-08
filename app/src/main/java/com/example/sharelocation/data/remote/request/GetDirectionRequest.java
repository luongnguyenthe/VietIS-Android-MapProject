package com.example.sharelocation.data.remote.request;

import com.example.sharelocation.data.OnRequestDataListener;
import com.google.android.gms.maps.model.LatLng;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetDirectionRequest extends HttpGetRequest<List<LatLng>> {
    public GetDirectionRequest(OnRequestDataListener<List<LatLng>> listener) {
        super(listener);
    }

    @Override
    List<LatLng> parseJSONToDataClass(String json) throws JSONException {
        List<LatLng> latLngs = new ArrayList<>();
        JSONArray jsonArray = new JSONObject(json)
                .getJSONObject("response")
                .getJSONArray("route").getJSONObject(0)
                .getJSONArray("leg").getJSONObject(0)
                .getJSONArray("maneuver");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("position");
            latLngs.add(new LatLng(jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude")));
        }

        return latLngs;
    }
}
