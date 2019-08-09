package com.example.sharelocation.screens.map;

import android.util.Log;
import com.example.sharelocation.data.OnRequestDataListener;
import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.data.repository.MapRepository;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapScreenPresenter implements MapScreenContract.Presenter {

    private MapRepository mMapRepository;
    private MapScreenContract.View mView;

    public MapScreenPresenter(MapRepository mapRepository) {
        mMapRepository = mapRepository;
    }

    @Override
    public void setView(MapScreenContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void findPlace(String name) {
        mView.showIndicatorForFindingPlace();
        mMapRepository.findPlace(name, new OnRequestDataListener<List<Place>>() {
            @Override
            public void onRequestDataSuccess(List<Place> places) {
                mView.hideIndicatorForFindingPlace();
                if (places == null || places.isEmpty()) {
                    this.onRequestDataFailure("");
                } else {
                    mView.findPlaceRequestSuccessful(places);
                }
            }


            @Override
            public void onRequestDataFailure(String message) {
                mView.hideIndicatorForFindingPlace();
                mView.findPlaceRequestFailed("Không tìm thấy địa điểm!");
            }
        });
    }

    @Override
    public void savePlace(Place place) {
        mMapRepository.savePlace(place);
    }

    @Override
    public void getAllPlaceHistory() {
        mView.showListPlaceHistory(mMapRepository.getAllPlaceSearched());
    }

    @Override
    public void findDirection(LatLng start, LatLng end) {
        mView.showIndicatorForFindingDirection();
        mMapRepository.findDirection(start, end, new OnRequestDataListener<List<LatLng>>() {
            @Override
            public void onRequestDataSuccess(List<LatLng> latLngList) {
                mView.hideIndicatorForFindingDirection();
                if (latLngList == null || latLngList.isEmpty()) {
                    this.onRequestDataFailure("");
                } else {
                    mView.findDirectionSuccessful(latLngList);
                }
            }

            @Override
            public void onRequestDataFailure(String message) {
                mView.hideIndicatorForFindingDirection();
                mView.findDirectionFailed("Không tìm thấy đường!");
            }
        });
    }

}
