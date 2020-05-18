package com.inegru.siit.myapplication.week5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.inegru.siit.myapplication.R;

public class ThreeButtonActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String EXTRA_TEXT_LONG = "extra_text_long";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_three_button);

        // set the click listener for all buttons
        findViewById(R.id.btnOne).setOnClickListener(this);
        findViewById(R.id.btnTwo).setOnClickListener(this);
        findViewById(R.id.btnThree).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // get the resource id base on which button was clicked
        int resId = getResForLongText(v.getId());
        // Create the intent and start the new activity
        startActivity(createIntentForGoNext(resId));
    }

    @StringRes
    private int getResForLongText(@IdRes int id) {
        int resId;
        switch (id) {
            case R.id.btnOne:
                resId = R.string.one_word;
                break;
            case R.id.btnTwo:
                resId = R.string.two_word;
                break;
            case R.id.btnThree:
                resId = R.string.three_word;
                break;
            default:
                resId = 0;
        }
        return resId;
    }

    @NonNull
    private Intent createIntentForGoNext(@StringRes int stringRes) {
        Intent intent = new Intent(this, SecondActivity.class);
        // set the string on the intent bundle only if it is valid
        if (stringRes != 0) {
            // format the text
            String text = getString(R.string.text_long_label,
                    getString(stringRes),
                    getString(R.string.lorem_ipsum_long));
            // set the text in the intent bundle
            intent.putExtra(EXTRA_TEXT_LONG, text);
        }
        return intent;
    }
}
