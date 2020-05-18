package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.routeoptimizer.R;

/**
 * @Author Harun Can Surav
 * This class is used by user to access properties of the app such as changing between map view and
 * satellite view.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
    }
    /**Called when user taps Map Locations button */
    public void mapLocations(View view) {
        Intent intent = new Intent(this, MapLocationsActivity.class);
        startActivity(intent);
    }

    /**Called when user taps Accuracy Level button */
    public void accuracyLevel(View view) {
        Intent intent;
        //startActivity(intent);
    }
}