package com.example.sharelocation.screens.map;

import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.screens.BasePresenter;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface MapScreenContract {
    interface View {

        void showListPlaceHistory(List<Place> places);

        void showIndicatorForFindingPlace();

        void hideIndicatorForFindingPlace();

        void findPlaceRequestSuccessful(List<Place> places);

        void findPlaceRequestFailed(String message);

        void showIndicatorForFindingDirection();

        void hideIndicatorForFindingDirection();

        void findDirectionSuccessful(List<LatLng> latLngList);

        void findDirectionFailed(String message);
    }

    interface Presenter extends BasePresenter<MapScreenContract.View> {
        void findPlace(String name);

        void findDirection(LatLng start, LatLng end);

        void savePlace(Place place);

        void getAllPlaceHistory();
    }
}
