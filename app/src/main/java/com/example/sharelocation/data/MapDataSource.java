package com.example.sharelocation.data;

import com.example.sharelocation.data.model.Place;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface MapDataSource {
    interface LocalDataSource {
        void savePlace(Place place);

        List<Place> getAllPlaceSearched();
    }

    interface RemoteDataSource {
        void findPlace(String name, OnRequestDataListener<List<Place>> listener);

        void findDirection(LatLng start, LatLng end, OnRequestDataListener<List<LatLng>> listener);
    }
}
