package com.example.sharelocation.data;

import com.example.sharelocation.data.model.Place;

import java.util.List;

public interface MapDataSource {
    interface LocalDataSource {

    }

    interface RemoteDataSource {
        void findPlace(String name, OnRequestDataListener<List<Place>> listener);
    }
}
