package com.example.sharelocation.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sharelocation.data.local.database.dao.PlaceDAO;
import com.example.sharelocation.data.model.Place;

@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlaceDAO placeDAO();
}