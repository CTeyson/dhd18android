package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.location;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unikoeln.mazey.dhdexamplesecond.R;

public class MapFragment extends Fragment {

    private MapView mapView;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        this.initMap();

        return view;
    }

    private void initMap() {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                googleMap = gMap;
                setPositionAndMarkerForConference();
            }
        });
    }

    private void setPositionAndMarkerForConference() {
        LatLng cologneUniversityMainBuilding = new LatLng(50.928124, 6.928649);
        LatLng cologneUniversitySeminarBuilding = new LatLng(50.926891, 6.927229);
        googleMap.addMarker(new MarkerOptions().position(cologneUniversityMainBuilding).title("Dhd Konferenz 2018").snippet("Hauptgebäude"));
        googleMap.addMarker(new MarkerOptions().position(cologneUniversitySeminarBuilding).title("Dhd Konferenz 2018").snippet("Seminargebäude"));

        CameraPosition cameraPositionMain = new CameraPosition.Builder().target(cologneUniversityMainBuilding).zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositionMain));
    }

}
