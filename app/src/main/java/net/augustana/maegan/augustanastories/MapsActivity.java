package net.augustana.maegan.augustanastories;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_READ_LOCATION = 1;
    private GoogleMap mMap;
    private Button nextButton;
    private Button previousButton;
    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        currentMarker = null;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent startIntent = this.getIntent();
        String locations = startIntent.getStringExtra("locations");
        Log.d("myTag", "loc" + locations);

        final ArrayList<Marker> markers = new ArrayList<>();

        final StoryCollection collection = StoryCollection.getDefaultStoryCollection(locations);

        int i = 1;
        for(StoryLocation story : collection.getStoryList()) {
            LatLng loc = new LatLng(story.getLat(), story.getLng());
            Marker marker = mMap.addMarker(new MarkerOptions().position(loc).title(i + ". " + story.getName()));
            markers.add(marker);
            i++;
        }


        if (!enableMyLocation()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_LOCATION);
        }

        StoryLocation oldMain = collection.getStoryByName("Old Main");
        LatLng oldMainLoc = new LatLng(oldMain.getLat(), oldMain.getLng());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oldMainLoc, 17));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                currentMarker = marker;
                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Toast.makeText(MapsActivity.this, marker.getTitle() + "\nhttp://lovelace.augustana.edu/AugustanaStories/" + marker.getTitle().toLowerCase() +
                //        ".html", Toast.LENGTH_LONG).show();
               // AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                //builder.setMessage("http://lovelace.augustana.edu/AugustanaStories/" + marker.getTitle().toLowerCase() + ".html").setTitle(marker.getTitle());
                //AlertDialog dialog = builder.create();
                //dialog.show();
                int space = marker.getTitle().indexOf(" ");
                String title = marker.getTitle().substring(space + 1);

                StoryLocation storyLoc = collection.getStoryByName(title);
                Intent intent = new Intent(getBaseContext(), StoryActivity.class);
                intent.putExtra(StoryActivity.URL_EXTRA, storyLoc.getUrl());

                startActivity(intent);
            }
        });

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMarker == null || currentMarker.equals(markers.get(markers.size() - 1))) {
                    currentMarker = markers.get(0);
                } else {
                    currentMarker = markers.get(markers.indexOf(currentMarker) + 1);
                }
                currentMarker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(currentMarker.getPosition()), 500, null);
            }
        });

        previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMarker == null || currentMarker.equals(markers.get(0))) {
                    currentMarker = markers.get(markers.size() - 1);
                } else {
                    currentMarker = markers.get(markers.indexOf(currentMarker) - 1);
                }
                currentMarker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(currentMarker.getPosition()), 500, null);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   enableMyLocation();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    public boolean enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            return true;
        } else {
            return false;
        }
    }


}
