package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.routeoptimizer.R;
import com.google.android.gms.maps.GoogleMap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author Harun Can Surav
 * This class works as a starting screen for the app and contains the pathway to Tutorial, Enter Locations,
 * Quit and Settings (deleted); also creates text files used for storing the data if such files does
 * not exist.
 */
public class MainActivity extends AppCompatActivity {


    final static String FILE_NAME = "coordinatesList.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            GoogleMap mMap = null;
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, 1,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");
            locationReset(FILE_NAME);
            locationReset("optimizedList.txt");
            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).apply();
        }
    }

    /**Called when user clicks Enter Locations button */
    public void enterLocations(View view) {
        Intent intent = new Intent(this, EnterLocationsActivity.class);
        startActivity(intent);
    }

    /**Called when user clicks Settings button */
    public void settingsButton(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Called when user taps on Exit button, which closes the program and clears text files
     */
    public void exitButtonAction(View view) {
        finish();
        locationReset(FILE_NAME);
        locationReset("optimizedList.txt");
        System.exit(0);
    }

    /**
     * Called when the user taps the Tutorial button
     */
    public void tutorialStart(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }

    /**
     * Resets the file named fileName
     * @param filename the file you want to reset
     */
    public void locationReset(String filename) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, MODE_PRIVATE);
            try {
                fos.write(("").getBytes());
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
