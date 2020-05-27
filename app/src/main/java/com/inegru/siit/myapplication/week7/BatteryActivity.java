package com.inegru.siit.myapplication.week7;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.inegru.siit.myapplication.R;

public class BatteryActivity extends AppCompatActivity {

    private ImageView battery;

    private int currentLevel = 1; // initial level
    private View btnIncrement;
    private View btnDecrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        battery = findViewById(R.id.battery);
        // Just to enabled/disable the buttons
        btnDecrement = findViewById(R.id.minus);
        btnIncrement = findViewById(R.id.plus);

        updateBatteryLevel(currentLevel);
    }

    public void onBatteryChangeDecrement(View view) {
        updateBatteryLevel(--currentLevel);
        updateButtonsState(currentLevel);
    }

    public void onBatteryChangeIncrement(View view) {
        updateBatteryLevel(++currentLevel);
        updateButtonsState(currentLevel);
    }

    private void updateButtonsState(int level) {
        btnDecrement.setEnabled(level > 1);
        btnIncrement.setEnabled(level < 7);
    }

    private void updateBatteryLevel(int level) {
        battery.setImageLevel(level);

        // Work-around for programmatically updating the level when xml does not work
//        battery.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.battery_level));
//        battery.refreshDrawableState();
    }
}
