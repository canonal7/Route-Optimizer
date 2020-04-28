package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.routeoptimizer.R;

public class EnterLocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_locations);
        Intent intent = getIntent();
    }
    /**Called when the users taps Enter Coordinates button */
    public void enterCoordinates(View view) {
        Intent intent = new Intent(this, EnterCoordinatesActivity.class);
        startActivity(intent);
    }
}
