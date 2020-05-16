package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.routeoptimizer.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import EdgePack.EdgeList;
import NodePack.Node;
import NodePack.NodeList;

public class EnterLocationsActivity extends AppCompatActivity {

    NodeList rawNodes = new NodeList();
    NodeList editedNodes = new NodeList();
    FileInputStream fis;
    FileOutputStream fos;
    String rawNodesString;
    String optimizedNodesString;
    String[] stringArray;
    final static String FILE_NAME = "coordinatesList.txt";
    final static String OPTIMIZED_FILE_NAME = "optimizedList.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_locations);
    }
    /**
     * Called when the users taps Enter Coordinates button
     * */
    public void enterCoordinates(View view) {
        Intent intent = new Intent(this, EnterCoordinatesActivity.class);
        startActivity(intent);
    }

    public void selectFromMap(View view) {
        Intent intent = new Intent(this, SelectFromMapActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps Edit button
     */
    public void editLocations(View view) {
        Intent intent = new Intent(this, EditLocationsActivity.class);
        if(! getLocationList().contains("."))
            Toast.makeText(this ,"Please enter a value",Toast.LENGTH_LONG).show();
        else
        startActivity(intent);

    }
    /**
     * Called when the user taps Done button
     */
    public void doneButtonAction(View view) {

        // Calculations


        // Extracting the text to a String
        rawNodesString = getLocationList();
        // Checking if the location is empty or not
        if (rawNodesString.equals("")) {
            Toast.makeText(this, "Please enter some values", Toast.LENGTH_LONG).show();
        }
        else if (rawNodesString.split("\n").length == 1) {
            Toast.makeText(this, "Please enter more than 1 value", Toast.LENGTH_LONG).show();
        }
        else {
            // Splitting the String into a String array
            stringArray = rawNodesString.split("\n");
            // Adding the elements into the NodeList
            for (String s : stringArray) {
                // Getting the x value
                double x = getX(s);
                // Getting the y value
                double y = getY(s);
                rawNodes.add(new Node(x, y));
            }
            // Copying the rawNodes into editedNodes
            editedNodes = rawNodes;
            // Running the algorithm
            editedNodes = algorithmNN(editedNodes);
            editedNodes = editedNodes.calculateTwoOpt(editedNodes);
            // Extracting the string from optimizedNodes
            for (int i = 0; i < editedNodes.size(); i++) {
                if (editedNodes.get(i) != null)
                    optimizedNodesString += editedNodes.get(i).getX() + " " + editedNodes.get(i).getY() + "\n";
            }
            System.out.println(optimizedNodesString + " Before null deletion");
            if(optimizedNodesString.contains("null"))
                optimizedNodesString = optimizedNodesString.substring(optimizedNodesString.indexOf("null") + 4);
            System.out.println(optimizedNodesString + "After null deletion");

            // Writing the previously extracted string into a text file
            setOptimizedNodesString(OPTIMIZED_FILE_NAME);

            // Starting the Map
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            optimizedNodesString = "";
            rawNodesString = "";
            rawNodes = new NodeList();
            editedNodes = new NodeList();
        }

    }

    /**
     *
     * @return the ingredients of the text file with the name fileName
     */
    private String getLocationList() {
        String coordinatesSoFar = "";
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
        return coordinatesSoFar;
    }

    /**
     *
     * @param coordinateElement the piece of string which the user wants to get the x element of
     * @return the x element
     */
    public double getX(String coordinateElement) {
        String[] strings = coordinateElement.split(" ");
        return Double.parseDouble(strings[0]);
    }

    /**
     *
     * @param coordinateElement is the string which you want to get the y ingredient
     * @return the y ingredient of given string
     */
    public double getY(String coordinateElement) {
        String[] strings = coordinateElement.split(" ");
        return Double.parseDouble(strings[1]);
    }

    /**
     * creates a text file from the string optimizedNodesString
     * @param fileName is the name of the file you want ot create
     */
    public void setOptimizedNodesString(String fileName) {

        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            try {
                fos.write(optimizedNodesString.getBytes());
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


    public static NodeList algorithmNN( NodeList n )
    {
        EdgeList temp = new EdgeList( n);
        temp.nearestNeighbor();
        return temp.extractNodeList();
    }
}
