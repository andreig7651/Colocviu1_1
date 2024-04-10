package com.example.colocviu1_1mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private EditText inputHistory;

    private Button navigateToSecondActivity;

    private int serviceStatus = 0;

    private final IntentFilter intentFilter = new IntentFilter();

    private final ButtonClickListener buttonClickListener = new ButtonClickListener();

    private final MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private static class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, Objects.requireNonNull(intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA)));
        }
    }
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.northButton) {
                Constants.NR_OF_CLICKS ++;
                inputHistory.setText(inputHistory.getText() + "North, ");
            }

            if (view.getId() == R.id.southButton) {
                Constants.NR_OF_CLICKS ++;
                inputHistory.setText(inputHistory.getText() + "South, ");
            }

            if (view.getId() == R.id.eastButton) {
                Constants.NR_OF_CLICKS ++;
                inputHistory.setText(inputHistory.getText() + "East, ");
            }

            if (view.getId() == R.id.westButton) {
                Constants.NR_OF_CLICKS ++;
                inputHistory.setText(inputHistory.getText() + "West, ");
            }

            if (Constants.NR_OF_CLICKS >= 4 && serviceStatus == 0) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
                intent.putExtra(Constants.CURRENT_INSTRUCTIONS, inputHistory.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = 1;
            }

            if (view.getId() == R.id.navigateToSecondActivity) {
                Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                Constants.NR_OF_CLICKS = 0;
                inputHistory.setText("");
                intent.putExtra(Constants.CURRENT_INSTRUCTIONS, inputHistory.getText());
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.NUMBER_OF_CLICKS, String.valueOf(Constants.NR_OF_CLICKS));
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

        intentFilter.addAction("read instructions");

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(messageBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(messageBroadcastReceiver, intentFilter);
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_1Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}