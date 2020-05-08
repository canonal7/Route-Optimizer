package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.routeoptimizer.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Node_Package.Node;

public class EnterCoordinatesActivity extends AppCompatActivity {

    final static String FILE_NAME = "coordinatesList.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_coordinates);
    }

    /**Called when user clicks Enter button */
    public void coordinateEntered(View view) {
        // Getting the data from EditTexts
        double xCor;
        double yCor;
        int index = 0;
        String coordinates;
        String coordinatesSoFar = "";
        FileInputStream fis = null;
        FileOutputStream fos = null;

        EditText xCorText = (EditText) findViewById(R.id.editText2);
        EditText yCorText = (EditText) findViewById(R.id.editText3);
        xCor = Double.parseDouble(xCorText.getText().toString());
        yCor = Double.parseDouble(yCorText.getText().toString());
        Node location = new Node(xCor, yCor);
        coordinates =  location.toString();
        xCorText.setText("");
        yCorText.setText("");
        // Checking if it works
        System.out.println(xCor);
        System.out.println(yCor);
        // Reading the prior data
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
            coordinatesSoFar += coordinates;
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

        // Saving the data

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            try {
                fos.write(coordinatesSoFar.getBytes());
                Toast.makeText(this, "Coordinate data is saved.", Toast.LENGTH_LONG).show();
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
