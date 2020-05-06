package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.routeoptimizer.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MapLocationsActivity extends AppCompatActivity {

    private static final String FILE_NAME = "userMapData.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_locations);
        loadLocationData();
    }

    /**
     * Save buttons onClick method to save the location data into userMapData.txt when the user taps on it
     * @param view
     */
    public void saveButtonAction(View view) {
        // Getting the String
        AutoCompleteTextView city = findViewById(R.id.autoCompleteTextView);
        AutoCompleteTextView country = findViewById(R.id.autoCompleteTextView2);
        AutoCompleteTextView continent = findViewById(R.id.autoCompleteTextView3);
        String cityText = city.getText().toString();
        String countryText = country.getText().toString();
        String continentText = continent.getText().toString();
        String locData = cityText + ", " + countryText + ", " + continentText;
        System.out.println(locData);
        // Saving the String
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            try {
                fos.write(locData.getBytes());
                Toast.makeText(this, "Location data is saved.", Toast.LENGTH_LONG).show();
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

    /**
     * Loads location data into the TextViews when called.
     */
    public void loadLocationData() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            String[] locInfo = sb.toString().split(", ");
            AutoCompleteTextView city = findViewById(R.id.autoCompleteTextView);
            AutoCompleteTextView country = findViewById(R.id.autoCompleteTextView2);
            AutoCompleteTextView continent = findViewById(R.id.autoCompleteTextView3);
            city.setText(locInfo[0]);
            country.setText(locInfo[1]);
            continent.setText(locInfo[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    }


}
