package com.example.sharelocation.screens;

public interface BasePresenter<T> {
    void setView(T t);

    void onStart();

    void onStop();
}
