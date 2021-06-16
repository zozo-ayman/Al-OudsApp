package com.example.alqudsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.alqudsapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng Jerusalem = new LatLng(31.79309, 35.19905);
        //  LatLng Jerusalem = new LatLng(31.768282462796645, 35.21312711315983);
        map.addMarker(new MarkerOptions() .position(Jerusalem).title("Jerusalem"));

        map.moveCamera(CameraUpdateFactory.newLatLng(Jerusalem));
        float zoomLevel = 16.0f;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Jerusalem, zoomLevel));

        //   LatLng Jerusalem = new LatLng(31.79309, 35.19905);
        //  LatLng mountainView = new LatLng(32.768282462796645, 34.21312711315983);

    }

}