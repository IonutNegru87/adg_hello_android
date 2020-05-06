package com.inegru.siit.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.inegru.siit.myapplication.week4.RecyclerViewActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Redirect to the desired activity
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }
}
