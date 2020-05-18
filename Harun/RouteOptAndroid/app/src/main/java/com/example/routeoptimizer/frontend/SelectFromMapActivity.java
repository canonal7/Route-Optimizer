package com.example.routeoptimizer.frontend;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.routeoptimizer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author Harun Can Surav
 * This class is used to select locations from the world map
 */
public class SelectFromMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String FILE_NAME = "coordinatesList.txt";
    private static final String TAG = SelectFromMapActivity.class.getSimpleName();
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
     * we just add a marker near Bilkent, Ankara.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Uploading the stylized map
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

        // Creating a default location (Bilkent University)
        LatLng marker = new LatLng(39.875356, 32.747489);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            // Are not used
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            // Are not used
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                currentMarker = marker.getPosition();
            }
        });
        // Adding the custom marker
        mMap.addMarker(new MarkerOptions().position(marker).icon(BitmapDescriptorFactory.
                defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).draggable(true));

        // Moving the marker to the position of marker
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    /**
     * Called when the user clicks on Select and adds the "currentMarker" to saved list of array.
     */
    public void enterLocation(View view) {
        String coordinates;
        String coordinatesSoFar;
        coordinates = currentMarker.latitude + " " + currentMarker.longitude;
        coordinatesSoFar = getContent(FILE_NAME);
        coordinatesSoFar += coordinates + "\n";
        writeFile(FILE_NAME, coordinatesSoFar);
        mMap.addMarker(new MarkerOptions().position(currentMarker).
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }

    /**
     * saves the contents of the file to a String
     * @param fileName the name of the file user wants to inspect
     * @return the ingredients of the file with the corresponding fileMame
     */
    public String getContent(String fileName) {
        FileInputStream fis = null;
        String content = "";
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            content = sb.toString();
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
        return content;
    }

    /**
     * Writes a specified string to a given location
     * @param fileName the name of the file
     * @param s the content which the method tries to write
     */
    public void writeFile(String fileName, String s) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            try {
                fos.write(s.getBytes());
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
