package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");
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
            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
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
