package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.example.routeoptimizer.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EnterLocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_locations);
        Intent intent = getIntent();
    }
    /**
     * Called when the users taps Enter Coordinates button
     * @param view
     * */
    public void enterCoordinates(View view) {
        Intent intent = new Intent(this, EnterCoordinatesActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps Edit button
     * @param view
     */
    public void editLocations(View view) {
        Intent intent = new Intent(this, EditLocationsActivity.class);
        startActivity(intent);
    }
    /**
     * Called when the user taps Done button
     */
    public void doneButtonAction(View view) {
        final String FILE_NAME = "coordinatesList.txt";
        String coordinatesSoFar = "";
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
            coordinatesSoFar = sb.toString();
            System.out.println(coordinatesSoFar);

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
