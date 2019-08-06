package com.example.sharelocation.screens.map;

import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.screens.BasePresenter;

import java.util.List;

public interface MapScreenContract {
    interface View {
        void showIndicatorForFindingPlace();

        void hideIndicatorForFindingPlace();

        void findPlaceRequestSuccessful(List<Place> places);

        void findPlaceRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<MapScreenContract.View> {
        void findPlace(String name);
    }
}
