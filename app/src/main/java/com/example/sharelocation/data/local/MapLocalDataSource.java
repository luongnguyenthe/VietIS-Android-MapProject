package com.example.sharelocation.data.local;


import android.content.Context;
import androidx.room.Room;
import com.example.sharelocation.data.MapDataSource;
import com.example.sharelocation.data.local.database.AppDatabase;
import com.example.sharelocation.data.model.Place;

import java.util.List;

public class MapLocalDataSource implements MapDataSource.LocalDataSource {

    private static MapLocalDataSource sInstance;
    private AppDatabase mAppDatabase;

    private MapLocalDataSource(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    public static MapLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            AppDatabase appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "map-database")
                    .allowMainThreadQueries()
                    .build();
            sInstance = new MapLocalDataSource(appDatabase);
        }

        return sInstance;
    }

    @Override
    public void savePlace(Place place) {
        mAppDatabase.placeDAO().insert(place);
    }

    @Override
    public List<Place> getAllPlaceSearched() {
        return mAppDatabase.placeDAO().getAllPlaces();
    }
}
