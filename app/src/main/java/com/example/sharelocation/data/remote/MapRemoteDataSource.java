package com.example.sharelocation.data.remote;

import com.example.sharelocation.data.MapDataSource;
import com.example.sharelocation.data.OnRequestDataListener;
import com.example.sharelocation.data.model.Place;

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
}
