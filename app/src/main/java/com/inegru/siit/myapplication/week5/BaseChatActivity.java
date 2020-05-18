package com.inegru.siit.myapplication.week5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.inegru.siit.myapplication.R;

import static com.inegru.siit.myapplication.utils.KeyboardHelper.hideKeyboard;

abstract class BaseChatActivity extends AppCompatActivity {

    static final String EXTRA_INPUT_DATA = "extra_input_data";

    private EditText inputField;
    private TextView textDisplay;
    private Button btnSend;
    private final TextWatcher realTimeInputDataValidator = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // no-op
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // no-op
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Disable the send button when data is not valid
            btnSend.setEnabled(isInputDataValid(s));
        }

        private boolean isInputDataValid(Editable inputData) {
            // Null & empty is invalid data
            return inputData != null && inputData.length() != 0;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        inputField = findViewById(R.id.etMessage);
        inputField.addTextChangedListener(realTimeInputDataValidator);

        btnSend = findViewById(R.id.btnSendMessage);

        // Set the label
        TextView chatLabel = findViewById(R.id.chatTypeLabel);
        chatLabel.setText(getChatLabel());

        textDisplay = findViewById(R.id.textDisplay);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Each time the activity is resumed we clear the input
        clearInputField();
    }

    @StringRes
    protected abstract int getChatLabel();

    // From the layout - must be public
    public void sendMessage(View view) {
        Editable inputData = inputField.getText();
        Intent intent = createSendingIntent();
        intent.putExtra(EXTRA_INPUT_DATA, inputData.toString());
        onSendMessage(intent);
    }

    private void clearInputField() {
        // Reset the input - hint will be displayed
        inputField.setText(null);
        // Clearing the focus will force the user to tap again to write a new message
        inputField.clearFocus(); // comment to keep focus between chat messages
        // Hide the keyboard also to have the screen in its initial state
        hideKeyboard(inputField.getContext(), inputField); // comment to keep the keyboard up
    }

    void onMessageReceived(@NonNull String message) {
        // Update the display message
        textDisplay.setText(message);
    }

    abstract Intent createSendingIntent();

    abstract void onSendMessage(Intent intent);

}
