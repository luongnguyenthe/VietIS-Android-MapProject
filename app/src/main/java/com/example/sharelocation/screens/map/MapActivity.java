package com.example.sharelocation.screens.map;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sharelocation.R;
import com.example.sharelocation.data.local.MapLocalDataSource;
import com.example.sharelocation.data.model.Place;
import com.example.sharelocation.data.remote.MapRemoteDataSource;
import com.example.sharelocation.data.repository.MapRepository;
import com.example.sharelocation.screens.BaseActivity;
import com.example.sharelocation.screens.OnItemRecyclerViewClickListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.widget.RelativeLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MapActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener, MapScreenContract.View {

    // Final variables
    private static final int REQUEST_CODE_LOCATION = 111;

    // View variables
    private GoogleMap mGoogleMap;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private SearchView mSearchView;
    private DrawerLayout mDrawerLayout;
    private NestedScrollView mNestedScrollView;
    private View mViewBackground;
    private PlaceSearchRVA mPlaceSearchRVA;
    private ProgressBar mLoadingIndicatorSearch;

    private MapScreenContract.Presenter mPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_map;
    }

    @Override
    protected void initComponents(@Nullable Bundle savedInstanceState) {
        mPresenter = new MapScreenPresenter(MapRepository.getInstance(MapLocalDataSource.getInstance(), MapRemoteDataSource.getInstance()));
        mPresenter.setView(this);

        mNestedScrollView = findViewById(R.id.nested_scroll_view_search_result);
        mViewBackground = findViewById(R.id.view_background);
        mLoadingIndicatorSearch = findViewById(R.id.progress_bar_search);

        initGoogleMap();
        initToolBarAndDrawerLayout();
        initSearchView();
        initRecyclerViews();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        moveButtonLocation();
        requestLocationPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient();
                } else {
                    Toast.makeText(this, "permission denied...", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onClick(View view) {
    }

    @SuppressLint("ResourceType")
    private void moveButtonLocation() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragment_map);
        View mapView = mapFragment.getView();
        if (mapView != null && mapView.findViewById(1) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 30);
        }
    }

    @Override
    public void showIndicatorForFindingPlace() {
        mLoadingIndicatorSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIndicatorForFindingPlace() {
        mLoadingIndicatorSearch.setVisibility(View.GONE);
    }

    @Override
    public void findPlaceRequestSuccessful(List<Place> places) {
        mPlaceSearchRVA.setPlaces(places);
    }

    @Override
    public void findPlaceRequestFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerViews() {
        mPlaceSearchRVA = new PlaceSearchRVA(this, new OnItemRecyclerViewClickListener<Place>() {
            @Override
            public void onItemRecyclerViewClick(RecyclerView recyclerView, int position, Place data) {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_place_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(mPlaceSearchRVA);
    }


    private void initSearchView() {
        mSearchView = findViewById(R.id.search_view);

        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    mNestedScrollView.setVisibility(View.VISIBLE);
                    mViewBackground.setVisibility(View.VISIBLE);
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    mViewBackground.setVisibility(View.GONE);
                    mNestedScrollView.setVisibility(View.GONE);
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                }
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPlaceSearchRVA.clearPlaces();
                if (!query.equals("")) mPresenter.findPlace(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
                .findFragmentById(R.id.fragment_map);
        supportMapFragment.getMapAsync(this);
    }

    private void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_LOCATION);
            } else {
                buildGoogleApiClient();
            }
        } else {
            buildGoogleApiClient();
        }
    }

    @SuppressLint("MissingPermission")
    private void buildGoogleApiClient() {
        mGoogleMap.setMyLocationEnabled(true);
        // Others variables
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    moveCameraMap(new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude()));
                } else {
                    mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                }
            }
        });
    }

    private void moveCameraMap(LatLng latLng) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
