package com.example.routeoptimizer.frontend;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.routeoptimizer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectFromMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final static String FILE_NAME = "coordinatesList.txt";

    LatLng currentMarker = new LatLng(39.875356, 32.747489);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_from_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

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
        mMap = googleMap;
        LatLng marker = new LatLng(39.875356, 32.747489);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                currentMarker = marker.getPosition();
            }
        });
        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(marker).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    public void enterLocation(View view) {
        String markerData;
        String coordinates;
        String coordinatesSoFar;
        String[] compounds;
        markerData = currentMarker.toString();
        markerData = markerData.substring(markerData.indexOf("(") + 1, markerData.length()-1);
        compounds = markerData.split(",");
        coordinates = compounds[0] + " " + compounds[1];
        coordinatesSoFar = readFile(FILE_NAME);
        coordinatesSoFar += coordinates + "\n";
        writeFile(FILE_NAME, coordinatesSoFar);
    }
    public void setMarker(LatLng marker) {

    }
    public String readFile(String fileName) {
        FileInputStream fis = null;
        String coordinatesSoFar;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            coordinatesSoFar = sb.toString();
            return coordinatesSoFar;

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
        return "";
    }

    public void writeFile(String fileName, String coordinatesSoFar) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            try {
                fos.write(coordinatesSoFar.getBytes());
                Toast.makeText(this, "Coordinate data is saved.", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(this, "Upload failed, try again", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Upload failed, try again", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
