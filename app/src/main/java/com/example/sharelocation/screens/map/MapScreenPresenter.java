package com.example.sharelocation.screens.map;

import com.example.sharelocation.data.OnRequestDataListener;
import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.data.repository.MapRepository;

import java.util.List;

public class MapScreenPresenter implements MapScreenContract.Presenter, OnRequestDataListener<List<Place>> {

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
        mMapRepository.findPlace(name, this);
    }

    @Override
    public void onRequestDataSuccess(List<Place> places) {
        mView.hideIndicatorForFindingPlace();
        mView.findPlaceRequestSuccessful(places);
    }

    @Override
    public void onRequestDataFailure(String message) {
        mView.hideIndicatorForFindingPlace();
        mView.findPlaceRequestFailed(message);
    }
}
