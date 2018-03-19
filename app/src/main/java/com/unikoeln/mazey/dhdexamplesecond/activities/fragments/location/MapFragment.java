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
	/** VIEW-INFLATING: Start inflater to bring xml-layout in existence as a view
	*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate designated xml-fragment "map_fragment"
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        // save view of the xml-element with the ID map_view in mapView
        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        // initialize map
        this.initMap();

        return view;
    }

	/** MAP-INITIALIZATION: Define initialization of the googlemap and then start methode for marker-setting setPositionAndMarkerForConference()
	*/

    private void initMap() {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                googleMap = gMap;
                setPositionAndMarkerForConference();
            }
        });
    }
	
	
	/** MARKER-SETTING: Save GPS-coordinates in LatLng properties, then place the markers on the map with the addMarker-methode, defining the coordinates via MarkerOptions()
	*/

    private void setPositionAndMarkerForConference() {
        // set GPS-coordinates for Markers
        //LatLng cologneUniversityMainBuilding = new LatLng(50.928124, 6.928649);
        LatLng cologneUniversitySeminarBuilding = new LatLng(50.926891, 6.927229);
        //LatLng cologneUniversityCafeteria = new LatLng(50.927824, 6.933278);
        LatLng cologneUniversityArtheatre = new LatLng(50.953100, 6.918708);
        LatLng cologneUniversityHoersaalBuilding = new LatLng(50.927306, 6.928301);

        LatLng CameraPositionCoordinates = new LatLng(50.941087, 6.927253);

        // set Marker on map and designate strings for markerinformation

        //googleMap.addMarker(new MarkerOptions().position(cologneUniversityMainBuilding).title(getString(R.string.map_marker_headline)).snippet(getString(R.string.map_marker_mainbuilding)));
        googleMap.addMarker(new MarkerOptions().position(cologneUniversitySeminarBuilding).title(getString(R.string.map_marker_headline)).snippet(getString(R.string.map_marker_seminarbuilding)));
        //googleMap.addMarker(new MarkerOptions().position(cologneUniversityCafeteria).title(getString(R.string.map_marker_headline)).snippet(getString(R.string.map_marker_mensa)));
        googleMap.addMarker(new MarkerOptions().position(cologneUniversityArtheatre).title(getString(R.string.map_marker_headline)).snippet(getString(R.string.map_marker_artheatre)));
        googleMap.addMarker(new MarkerOptions().position(cologneUniversityHoersaalBuilding).title(getString(R.string.map_marker_headline)).snippet(getString(R.string.map_marker_hoersaalbuilding)));

        CameraPosition cameraPositionMain = new CameraPosition.Builder().target(CameraPositionCoordinates).zoom(13).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositionMain));
    }

}
