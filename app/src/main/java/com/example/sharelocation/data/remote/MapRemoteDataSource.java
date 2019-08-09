package com.example.sharelocation.data.remote;

import com.example.sharelocation.data.MapDataSource;
import com.example.sharelocation.data.OnRequestDataListener;
import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.data.remote.request.FindPlaceRequest;
import com.example.sharelocation.data.remote.request.GetDirectionRequest;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapRemoteDataSource implements MapDataSource.RemoteDataSource {

    private static MapRemoteDataSource sMapRemoteDataSource;

    public static MapRemoteDataSource getInstance() {
        if (sMapRemoteDataSource == null) {
            sMapRemoteDataSource = new MapRemoteDataSource();
        }

        return sMapRemoteDataSource;
    }


    @Override
    public void findPlace(String name, OnRequestDataListener<List<Place>> listener) {
        name = name.replaceAll("\\s", "");
        new FindPlaceRequest(listener)
                .execute("https://places.cit.api.here.com/places/v1/autosuggest?app_id=Qx5JS0HLu1snSuxn1SXQ&app_code=azSrplQGcMhkfV-XA50rWw&at=16.000,106.000&q=" + name + "&pretty");
    }

    @Override
    public void findDirection(LatLng start, LatLng end, OnRequestDataListener<List<LatLng>> listener) {
        String startPoint = start.latitude + "," + start.longitude;
        String endPoint = end.latitude + "," + end.longitude;
        new GetDirectionRequest(listener)
                .execute("https://route.api.here.com/routing/7.2/calculateroute.json?waypoint0=" + startPoint + "&waypoint1=" + endPoint + "&mode=fastest%3Bbicycle%3Btraffic%3Adefault&app_id=Qx5JS0HLu1snSuxn1SXQ&app_code=azSrplQGcMhkfV-XA50rWw&departure=now");
    }
}
