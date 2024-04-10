package com.example.colocviu1_1mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class SecondaryActivity extends AppCompatActivity {

    private final ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() ==  R.id.register_button) {
                setResult(RESULT_OK, null);
            }
            if (view.getId() == R.id.cancel_button) {
                    setResult(RESULT_CANCELED, null);
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        TextView numberOfClicksTextView = (TextView) findViewById(R.id.number_of_clicks_text_view);
        Intent intent = getIntent();
        if (intent != null && Objects.requireNonNull(intent.getExtras()).containsKey(Constants.CURRENT_INSTRUCTIONS)) {
            String numberOfClicks = intent.getStringExtra(Constants.CURRENT_INSTRUCTIONS);
            numberOfClicksTextView.setText(numberOfClicks);
        }

        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(buttonClickListener);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}