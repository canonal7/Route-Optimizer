package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.routeoptimizer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
