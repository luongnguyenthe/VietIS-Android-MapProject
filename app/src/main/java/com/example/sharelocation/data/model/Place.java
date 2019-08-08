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

    public Double getLongtitude() {
        return mLongtitude;
    }

    public void setLongtitude(Double longtitude) {
        mLongtitude = longtitude;
    }

    @Override
    public String toString() {
        return "Place{" +
                "mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongtitude=" + mLongtitude +
                '}';
    }
}

