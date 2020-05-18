package com.example.routeoptimizer.frontend;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditLocationsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final static String FILE_NAME = "coordinatesList.txt";
    private static final String TAG = EditLocationsActivity.class.getSimpleName();
    String selectedNode = "";
    List<String> locationList = new ArrayList<>();
    ArrayList<Marker> markerArrayList = null;
    String[] soFarArray;
    private Spinner spinner;
    int indexOfSelectedLocation = 0;
    private GoogleMap mMap;
    LatLng[] coordinatesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_locations);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.showLocations);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // Creating FileInputStream to read the file that contains previously added locations
        FileInputStream fis = null;
        // Finding the spinner class which visualizes the Array
        spinner = findViewById(R.id.locationsCollection);
        // Adding default locationList just into the ArrayList but not the
        locationList.add("Select a location");

        // Reading the file, ohm, "try" to read *wink-wink
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            // Extracting the string out of StringBuilder
            String soFar = sb.toString();
            // Creating a temporary String array to split the data
            soFarArray = soFar.split("\n");
            // Adding the temporary String arrays content into locationList
            Collections.addAll(locationList, soFarArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    // Closing the FileInputStream
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Connecting the ArrayAdapter with locationList array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Connecting the ArrayAdapter with Spinner
        spinner.setAdapter(adapter);
        // Initializing the node with selected node
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNode = (String) parent.getSelectedItem();
                indexOfSelectedLocation = position;
                if(indexOfSelectedLocation > 0)
                    moveCamera(getX(selectedNode), getY(selectedNode));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    /**
     * Is called when the user taps on Remove button
     * @param view
     */
    public void removeButton(View view) {
        // Checking if the point selected is valid
        if (! locationList.get(indexOfSelectedLocation).equals("Select a location")) {
            locationList.remove(indexOfSelectedLocation);
            Toast.makeText(this, selectedNode + " is removed.", Toast.LENGTH_LONG).show();
            for (int i = 0; i < markerArrayList.size(); i++) {
                String coordinates = markerArrayList.get(i).getPosition().latitude + " " + markerArrayList.get(i).getPosition().longitude;
                if( coordinates.equals(selectedNode) ) {
                    markerArrayList.get(i).setAlpha(0);
                    break;
                }
            }
            indexOfSelectedLocation = 0;
            spinner.setSelection(0);
        }
        // Prompting the user if the user selects a non-valid object ("Select a location")
        else
            Toast.makeText(this, "Select a valid location", Toast.LENGTH_LONG).show();
        // Building the string
        StringBuilder coordinatesSoFar = new StringBuilder();
        for (int i = 1; i < locationList.size() ; i++) {
            coordinatesSoFar.append(locationList.get(i)).append("\n");
        }
        writeFile(FILE_NAME, coordinatesSoFar.toString());
    }


    /**
     * Called when the map is ready
     * @param googleMap the map we want to access and modify
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        coordinatesList = getLatLng(soFarArray);
        markerArrayList = new ArrayList<>();
        // Implementation of custom map
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
        // Loading the previous locations
        for (LatLng latLng : coordinatesList)
            markerArrayList.add(mMap.addMarker(new MarkerOptions().position(latLng).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
        // Listener
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String coordinate = marker.getPosition().latitude + " " + marker.getPosition().longitude;
                for (int i = 0; i < locationList.size(); i++) {
                    if(locationList.get(i).equals(coordinate)) {
                        spinner.setSelection(i);
                        break;
                    }
                }
                return false;
            }
            });
    }

    /**
     * Moves the camera to a specified point
     * @param x element of the location
     * @param y element of the location
     */
    public void moveCamera(double x, double y) {
        LatLng lng = new LatLng(x,y);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 6));
    }

    /**
     * Converts a suitable String array into LatLng array
     * @param s String array that the user wants to convert
     * @return LatLng array that is converted from s.
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
     * Overrides the String in 1st parameter with 2nd parameter
     * @param fileName the name of the file
     * @param s the string
     */
    public void writeFile(String fileName, String s) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            try {
                fos.write(s.getBytes());
                Toast.makeText(this, "Coordinate data is updated.", Toast.LENGTH_LONG).show();
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
