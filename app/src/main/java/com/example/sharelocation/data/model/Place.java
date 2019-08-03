package com.example.sharelocation.data.model;

public class Place {
    private String mName;
    private String mAddress;
    private Double mLatitude;
    private Double mLongtitude;

    public Place(String mName, String mAddress, Double mLatitude, Double mLongtitude) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mLatitude = mLatitude;
        this.mLongtitude = mLongtitude;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongtitude() {
        return mLongtitude;
    }

    public void setmLongtitude(Double mLongtitude) {
        this.mLongtitude = mLongtitude;
    }
}
