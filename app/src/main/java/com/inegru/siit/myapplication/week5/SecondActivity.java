package com.inegru.siit.myapplication.week5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inegru.siit.myapplication.R;

import static com.inegru.siit.myapplication.week5.ThreeButtonActivity.EXTRA_TEXT_LONG;

public class SecondActivity extends AppCompatActivity {

    private TextView tvLongText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_second);

        // init the view that will display the text
        tvLongText = findViewById(R.id.tvLongText);
        // handle the intent - get data from it's bundle
        handleReceivedIntent();
    }

    private void handleReceivedIntent() {
        String receivedText = null;
        Intent intent = getIntent();
        if (intent != null) { // for sanity checks: usually it cannot be null
            receivedText = intent.getStringExtra(EXTRA_TEXT_LONG);
        }
        if (receivedText != null) {
            showReceivedText(receivedText);
        } else {
            showNoTextReceived();
        }
    }

    private void showNoTextReceived() {
        tvLongText.setText(R.string.no_text_via_intent);
    }

    private void showReceivedText(@NonNull String receivedText) {
        tvLongText.setText(receivedText);
    }
}
