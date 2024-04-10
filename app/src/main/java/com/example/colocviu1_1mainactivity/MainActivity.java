package com.example.colocviu1_1mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private EditText inputHistory;

    private Button navigateToSecondActivity;

    private String result = "";

    private final ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.northButton) {
                result += "North, ";
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.southButton) {
                result += "South, ";
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.eastButton) {
                result += "East, ";
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.westButton) {
                result += "West, ";
                inputHistory.setText(result);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        northButton = findViewById(R.id.northButton);
        northButton.setOnClickListener(buttonClickListener);
        southButton = findViewById(R.id.southButton);
        southButton.setOnClickListener(buttonClickListener);
        eastButton = findViewById(R.id.eastButton);
        eastButton.setOnClickListener(buttonClickListener);
        westButton = findViewById(R.id.westButton);
        westButton.setOnClickListener(buttonClickListener);

        inputHistory = findViewById(R.id.inputHistory);

        navigateToSecondActivity = findViewById(R.id.navigateToSecondActivity);
        navigateToSecondActivity.setOnClickListener(buttonClickListener);

    }
}