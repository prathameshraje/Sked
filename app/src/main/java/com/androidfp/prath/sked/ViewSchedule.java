package com.androidfp.prath.sked;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewSchedule extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    MarkerOptions markerOptions;
    SupportMapFragment mapFragment;
    String EventID,skedTime;
    int UserID;
    SqlHelper sh = new SqlHelper(this);
    TextView textViewEventNameValue,textViewEventDetailsValue,textViewEventScheduleValue,textViewEventLocation;
    Double lat,lng;
    String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        Intent i = getIntent();
        EventID = i.getStringExtra("EventID");
        UserID = i.getIntExtra("UserID", 0);
        skedTime = i.getStringExtra("skedTime");
        Event event=new Event();
        event=sh.getEventDetails(EventID);
        lat=event.getLat();
        lng=event.getLng();
        add=event.getAddress();
        textViewEventNameValue=(TextView) findViewById(R.id.textViewEventNameValue);
        textViewEventDetailsValue=(TextView) findViewById(R.id.textViewEventDetailsValue);
        textViewEventScheduleValue=(TextView) findViewById(R.id.textViewScheduleValue);
        textViewEventLocation=(TextView) findViewById(R.id.textViewLocation);
        textViewEventNameValue.setText(event.getEventName());
        textViewEventDetailsValue.setText(event.getEventDesc());
        textViewEventScheduleValue.setText(skedTime);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(lat==0.0 && lng==0.0) {
            mapFragment.getView().setVisibility(View.INVISIBLE);
            textViewEventLocation.setVisibility(View.VISIBLE);
            textViewEventLocation.setText("Location Not Set For This Event");
        }
        else {
            textViewEventLocation.setVisibility(View.INVISIBLE);

            mapFragment.getMapAsync(this);
        }

    }
/*

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View InputFragmentView = inflater.inflate(R.layout.activity_view_schedule, container, false);
//Load Map View using Google Map API
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LatLng latLng = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(latLng).title(add));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        // Inflate the layout for this fragment
        return InputFragmentView;
    }*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(sydney).title(add));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14.0f));
    }
    }