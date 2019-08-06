package com.example.sharelocation.data;

import androidx.annotation.AnyThread;

public interface OnRequestDataListener<T> {
    void onRequestDataSuccess(T t);

    @AnyThread
    void onRequestDataFailure(String message);
}
