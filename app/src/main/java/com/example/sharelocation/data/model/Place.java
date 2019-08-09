package com.example.sharelocation.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Place {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mID;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "address")
    private String mAddress;

    @ColumnInfo(name = "latitude")
    private Double mLatitude;

    @ColumnInfo(name = "longitude")
    private Double mLongitude;

    public Place() {

    }

    public Place(int ID, String name, String address, Double latitude, Double longitude) {
        mID = ID;
        mName = name;
        mAddress = address;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    @Override
    public String toString() {
        return "Place{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}