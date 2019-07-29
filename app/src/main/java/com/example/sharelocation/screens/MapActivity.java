package com.example.sharelocation.screens;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.sharelocation.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mGoogleMap;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private SearchView mSearchView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_map;
    }

    @Override
    protected void initComponents(@Nullable Bundle savedInstanceState) {
        initGoogleMap();
        initToolBarAndDrawerLayout();
        initSearchView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    @Override
    public void onClick(View view) {
    }

    private void initSearchView() {
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                }
            }
        });
    }

    private void initToolBarAndDrawerLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchView.clearFocus();
            }
        });

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private void initGoogleMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
    }
}
