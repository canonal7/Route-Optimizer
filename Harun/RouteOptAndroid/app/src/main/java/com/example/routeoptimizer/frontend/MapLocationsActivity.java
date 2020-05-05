package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.example.routeoptimizer.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MapLocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_locations);
    }
    public void saveButtonAction(View view) {
        AutoCompleteTextView city = findViewById(R.id.autoCompleteTextView);
        AutoCompleteTextView country = findViewById(R.id.autoCompleteTextView2);
        AutoCompleteTextView continent = findViewById(R.id.autoCompleteTextView3);
        String cityText = city.getText().toString();
        String countryText = country.getText().toString();
        String continentText = continent.getText().toString();
        String locData = cityText + "\n" + countryText + "\n" + continentText;
        System.out.println(locData);
        readLocationDataFile();
    }
    public void readLocationDataFile() {
        System.out.println("Deneme");
        String s = "";
        try {
            InputStream inputStream = getAssets().open("userMapData.txt");
            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            s = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
