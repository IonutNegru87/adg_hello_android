package com.inegru.siit.myapplication.week3;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.inegru.siit.myapplication.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * .
 */
public class LoginActivity extends AppCompatActivity {

    private EditText edEmail;
    private EditText edPhone;
    private CheckBox chTermsConditions;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.et_email);
        edPhone = findViewById(R.id.et_phone);
        chTermsConditions = findViewById(R.id.cb_tc);

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chTermsConditions.isChecked()) {
                    handleSubmit(edEmail.getText(), edPhone.getText());
                } else {
                    Toast.makeText(LoginActivity.this,
                                   "You have to accept terms & conditions",
                                   Toast.LENGTH_SHORT)
                         .show();
                }
            }
        });
    }

    private void handleSubmit(CharSequence email, CharSequence phone) {
        boolean isError = false;
        if (email == null || email.length() ==0) {
            // email error
            edEmail.setError("Email cannot be empty");
            isError = true;
        }

        if (phone == null || phone.length() ==0) {
            // phone error
            edPhone.setError("Phone cannot be empty");
            isError = true;
        }

        if (!isError) {
            // Submit data
            Toast.makeText(this, "Well done", Toast.LENGTH_SHORT).show();
            // clear input data
            edEmail.setText("");
            edPhone.setText("");
        }
    }
}
