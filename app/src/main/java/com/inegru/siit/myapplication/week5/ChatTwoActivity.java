package com.inegru.siit.myapplication.week5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.inegru.siit.myapplication.R;

// Core logic is base activity
public class ChatTwoActivity extends BaseChatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleReceivedIntent();
    }

    private void handleReceivedIntent() {
        Intent intent = getIntent();
        if (intent != null) { // sanity check on intent
            String receivedMessage = intent.getStringExtra(EXTRA_INPUT_DATA);
            if (receivedMessage != null) { // Validate received message
                onMessageReceived(receivedMessage);
            }
        }
    }

    @Override
    protected int getChatLabel() {
        // The label for the responding activity
        return R.string.message_received;
    }

    @Override
    Intent createSendingIntent() {
        // Simple intent is enough as it will just encapsulate the data sent back
        return new Intent();
    }

    @Override
    void onSendMessage(Intent intent) {
        // Set the result code and the intent
        setResult(Activity.RESULT_OK, intent);
        // Upon finishing this activity the result will be sent to the owner activity
        finish();
    }
}
