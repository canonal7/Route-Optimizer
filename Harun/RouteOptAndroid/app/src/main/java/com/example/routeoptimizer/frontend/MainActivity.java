package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.routeoptimizer.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    final static String FILE_NAME = "coordinatesList.txt";
    FileOutputStream fos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
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
