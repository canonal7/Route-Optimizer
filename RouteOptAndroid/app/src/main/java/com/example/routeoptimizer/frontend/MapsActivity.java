package com.example.routeoptimizer.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.routeoptimizer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @Author Harun Can Surav
 * This class is used for displaying the locations and showing the route to user.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    private static final String TAG = MapsActivity.class.getSimpleName();
    FileInputStream fis = null;
    final String FILE_NAME = "optimizedList.txt";
    LatLng[] coordinatesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    public void testButton() {
        String locationList = getLocationList(FILE_NAME);
        String[] strings = locationList.split("\n");
        coordinatesList = getLatLng(strings);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);
        // Adding markers
        testButton();
        if(coordinatesList.length > 1) {
            for (int i = 0; i < coordinatesList.length - 1; i++) {
                if (i == 0) {
                    googleMap.addMarker(new MarkerOptions().position(coordinatesList[i]).title(" ").
                            snippet("Starting Point").icon(BitmapDescriptorFactory.
                            defaultMarker(BitmapDescriptorFactory.HUE_AZURE))).showInfoWindow();

                    continue;
                }
                googleMap.addMarker(new MarkerOptions().position(coordinatesList[i]).title(" ").
                        snippet((i + 1) + ". Location").icon(BitmapDescriptorFactory.
                        defaultMarker(235.0f))).showInfoWindow();
            }
        }
        // Move the camera to starting point
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinatesList[0]));
        // Creating the PolyLine
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true).add(coordinatesList));
        polyline1.setTag("A");
    }

    /**
     *
     * @param fileName the name of the file user wants to inspect
     * @return the ingredients of the file with the corresponding fileMame
     */
    public String getLocationList(String fileName) {

        String coordinatesSoFar = "";
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            coordinatesSoFar = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return coordinatesSoFar;
    }

    /**
     *
     * @param coordinateElement is the string which you want to get the x ingredient
     * @return the x ingredient at given string
     */
    public double getX(String coordinateElement) {
        String[] strings = coordinateElement.split(" ");
        return Double.parseDouble(strings[0]);
    }

    /**
     *
     * @param coordinateElement is the string which you want to get the y ingredient
     * @return the y ingredient of given string
     */
    public double getY(String coordinateElement) {
        String[] strings = coordinateElement.split(" ");
        return Double.parseDouble(strings[1]);
    }

    /**
     *
     * @param s String array which you want to convert to LatLng[]
     * @return the LatLng[] converted from the parameter
     */
    public LatLng[] getLatLng(String[] s) {
        LatLng[] locationList = new LatLng[s.length];
        int i = 0;
        for (String temp : s) {
            double x = getX(temp);
            double y = getY(temp);
            locationList[i] = new LatLng(x,y);
            i++;
        }
        return locationList;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
}
