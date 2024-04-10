package com.example.colocviu1_1mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private EditText inputHistory;

    private Button navigateToSecondActivity;

    private String result = "";

    private Integer numberOfPoints = 0;

    private final ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.northButton) {
                result += "North, ";
                numberOfPoints++;
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.southButton) {
                result += "South, ";
                numberOfPoints++;
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.eastButton) {
                result += "East, ";
                numberOfPoints++;
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.westButton) {
                result += "West, ";
                numberOfPoints++;
                inputHistory.setText(result);
            }

            if (view.getId() == R.id.navigateToSecondActivity) {
                Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                intent.putExtra(Constants.CURRENT_INSTRUCTIONS, result);
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.NUMBER_OF_CLICKS, numberOfPoints.toString());
        savedInstanceState.putString(Constants.INPUT_HISTORY, inputHistory.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.INPUT_HISTORY)) {
            inputHistory.setText(savedInstanceState.getString(Constants.INPUT_HISTORY));
        }

        if (savedInstanceState.containsKey(Constants.NUMBER_OF_CLICKS)) {
            Toast.makeText(this, "Button clicked " +
                    savedInstanceState.getString(Constants.NUMBER_OF_CLICKS) +
                    " times", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Button clicked " +
                            savedInstanceState.getString(Constants.NUMBER_OF_CLICKS) +
                    " times", Toast.LENGTH_LONG).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}