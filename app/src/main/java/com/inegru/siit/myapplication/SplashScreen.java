package com.inegru.siit.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inegru.siit.myapplication.week5.ImplicitIntentActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Redirect to the desired activity
        startActivity(new Intent(this, ImplicitIntentActivity.class));
    }
}
