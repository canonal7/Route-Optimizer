package com.example.routeoptimizer.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.routeoptimizer.R;

public class EnterCoordinatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_coordinates);
    }

    /**Called when user clicks Enter button */
    public void coordinateEntered(View view) {
        double xCor;
        double yCor;
        EditText xCorText = (EditText) findViewById(R.id.editText2);
        EditText yCorText = (EditText) findViewById(R.id.editText3);
        xCor = Double.parseDouble(xCorText.getText().toString());
        yCor = Double.parseDouble(yCorText.getText().toString());
        xCorText.setText("");
        yCorText.setText("");
        System.out.println(xCor);
        System.out.println(yCor);
    }
}
