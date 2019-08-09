package com.example.sharelocation.data.repository;

import com.example.sharelocation.data.MapDataSource;
import com.example.sharelocation.data.OnRequestDataListener;
import com.example.sharelocation.data.model.Place;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapRepository implements MapDataSource.LocalDataSource, MapDataSource.RemoteDataSource {

    private static MapRepository sInstance;

    private MapDataSource.RemoteDataSource mRemoteDataSource;
    private MapDataSource.LocalDataSource mLocalDataSource;

    private MapRepository(MapDataSource.LocalDataSource localDataSource, MapDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static MapRepository getInstance(MapDataSource.LocalDataSource localDataSource, MapDataSource.RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new MapRepository(localDataSource, remoteDataSource);
        }

        return sInstance;
    }

    @Override
    public void findPlace(String name, OnRequestDataListener<List<Place>> listener) {
        mRemoteDataSource.findPlace(name, listener);
    }

    @Override
    public void findDirection(LatLng start, LatLng end, OnRequestDataListener<List<LatLng>> listener) {
        mRemoteDataSource.findDirection(start, end, listener);
    }

    @Override
    public void savePlace(Place place) {
        mLocalDataSource.savePlace(place);
    }

    @Override
    public List<Place> getAllPlaceSearched() {
        return mLocalDataSource.getAllPlaceSearched();
    }
}
