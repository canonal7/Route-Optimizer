package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.routeoptimizer.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @Author Harun Can Surav
 * This class allows user to enter coordinates into TextFields and then stores them in a text file
 */
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
        String coordinates;
        String coordinatesSoFar;
        FileOutputStream fos = null;

        EditText xCorText = findViewById(R.id.latitude);
        EditText yCorText = findViewById(R.id.longitude);
        if (xCorText.getText().toString().trim().length() <= 0 || yCorText.getText().toString().trim().length() <= 0) {
            Toast.makeText(this, "Enter a value", Toast.LENGTH_LONG).show();
        }
        else if (Math.abs(Double.parseDouble(xCorText.getText().toString())) > 90 ||Math.abs(Double.parseDouble(yCorText.getText().toString())) > 180 ) {
            Toast.makeText(this, "Please enter a valid value", Toast.LENGTH_LONG).show();
        }
        else {
            xCor = Double.parseDouble(xCorText.getText().toString());
            yCor = Double.parseDouble(yCorText.getText().toString());
            // Creating the new Node
            coordinates = xCor + " " + yCor;
            xCorText.setText("");
            yCorText.setText("");
            // Reading the prior data
            coordinatesSoFar = getContent(FILE_NAME);
            // Adding the recent data
            coordinatesSoFar += coordinates + "\n";

            // Saving the data
            writeFile(FILE_NAME, coordinatesSoFar);
        }
    }

    /**
     *
     * @param fileName the name of the file user wants to inspect
     * @return the ingredients of the file with the corresponding fileMame
     */
    public String getContent(String fileName) {
        FileInputStream fis = null;
        String content = "";
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            content = sb.toString();
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
        return content;
    }

    /**
     * Overrides the String in 1st parameter with 2nd parameter
     * @param fileName the name of the file
     * @param s the string
     */
    public void writeFile(String fileName, String s) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            try {
                fos.write(s.getBytes());
                Toast.makeText(this, "Coordinate data is saved.", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(this, "Upload failed, try again", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Upload failed, try again", Toast.LENGTH_LONG).show();
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
