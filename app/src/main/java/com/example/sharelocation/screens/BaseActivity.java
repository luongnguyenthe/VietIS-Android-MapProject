package com.example.sharelocation.screens;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initComponents(savedInstanceState);
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void initComponents(@Nullable Bundle savedInstanceState);
}
