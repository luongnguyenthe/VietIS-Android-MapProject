package com.example.sharelocation.data.local.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sharelocation.data.model.Place;

import java.util.List;

@Dao
public interface PlaceDAO {

    @Query("SELECT * FROM places")
    List<Place> getAllPlaces();

    @Insert
    void insert(Place place);

}
