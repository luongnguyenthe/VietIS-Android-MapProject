package com.example.sharelocation.data;

public interface OnRequestDataListener<T> {
    void onRequestDataSuccess(T t);

    void onRequestDataFailure();
}
