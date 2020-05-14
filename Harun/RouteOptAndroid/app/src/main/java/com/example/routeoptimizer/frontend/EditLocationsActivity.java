package com.example.routeoptimizer.frontend;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.routeoptimizer.R;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditLocationsActivity extends AppCompatActivity{

    private Spinner spinner;
    MapView mapView = null;
    List<String> locationList = new ArrayList<>();
    String selectedNode = "";
    int indexOfSelectedLocation = 0;
    ArrayAdapter<String> adapter;
    FileOutputStream fos = null;
    final static String FILE_NAME = "coordinatesList.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_locations);
        // Obtaining mapView

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
            String[] soFarArray = soFar.split("\n");
            // Adding the temporary String arrays context into locationList
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayLocationInfo(String node) {
        Toast.makeText(this, node, Toast.LENGTH_LONG).show();
    }
    public void removeButton(View view) {
            if (! locationList.get(indexOfSelectedLocation).equals("Select a location")) {
                locationList.remove(indexOfSelectedLocation);
                Toast.makeText(this, selectedNode + " is removed.", Toast.LENGTH_LONG).show();
                spinner.setSelection(0);
            } else
                Toast.makeText(this, "Select a valid location", Toast.LENGTH_LONG).show();
            StringBuilder coordinatesSoFar = new StringBuilder();
        for (int i = 1; i < locationList.size() ; i++) {
            coordinatesSoFar.append(locationList.get(i)).append("\n");
        }
            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                try {
                    fos.write(coordinatesSoFar.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
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
