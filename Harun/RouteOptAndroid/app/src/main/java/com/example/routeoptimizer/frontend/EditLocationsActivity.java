package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.routeoptimizer.R;

import java.util.ArrayList;
import java.util.List;

import Node_Package.Node;
import Node_Package.NodeList;

public class EditLocationsActivity extends AppCompatActivity {

    private Spinner spinner;
    List<Node> locationList = new ArrayList<Node>();
    Node selectedNode = null;
    ArrayAdapter<Node> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_locations);

        spinner = findViewById(R.id.locationsCollection);

        locationList.add(new Node(60, 10));
        locationList.add(new Node(40, 20));
        locationList.add(new Node(50, 30));

        ArrayAdapter<Node> adapter = new ArrayAdapter<Node>(this,
                android.R.layout.simple_spinner_item, (List<Node>) locationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Node location = (Node) parent.getSelectedItem();
                displayLocationInfo(location);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getSelectedLocation(View view) {
        Node location = (Node) spinner.getSelectedItem();
        selectedNode = location;
        displayLocationInfo(location);
    }

    private void displayLocationInfo(Node node) {
        String toastInfo = node.toString();
        Toast.makeText(this, toastInfo, Toast.LENGTH_LONG).show();
    }
    public void removeButton(View view) {
        for (int i = locationList.size() - 1; i >= 0; i--) {
            if (locationList.get(i).toString().equals(selectedNode.toString()))
                {
                    locationList.remove(i);
                }
        }
    }
}
