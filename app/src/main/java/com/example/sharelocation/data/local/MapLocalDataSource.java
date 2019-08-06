package com.example.sharelocation.data.local;

import com.example.sharelocation.data.MapDataSource;

public class MapLocalDataSource implements MapDataSource.LocalDataSource {

    private static MapLocalDataSource sInstance;

    public static MapLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MapLocalDataSource();
        }

        return sInstance;
    }
}
