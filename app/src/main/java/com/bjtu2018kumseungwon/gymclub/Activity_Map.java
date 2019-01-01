package com.bjtu2018kumseungwon.gymclub;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Activity_Map extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {

        LatLng jiaotong = new LatLng(39.95, 116.34);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(jiaotong);
        markerOptions.title("GYM CLUB");
        markerOptions.snippet("It's a gymclub.");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(jiaotong));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}
