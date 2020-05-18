package com.inegru.siit.myapplication.week5;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.inegru.siit.myapplication.R;

// Core logic is base activity
public class ChatOneActivity extends BaseChatActivity {

    private static final int CHAT_ONE_MESSAGE_REQUEST = 101;

    @Override
    protected int getChatLabel() {
        // The label for the owner activity
        return R.string.reply_received;
    }

    @Override
    Intent createSendingIntent() {
        // Explicit intent specifying the destination activity
        return new Intent(this, ChatTwoActivity.class);
    }

    @Override
    void onSendMessage(Intent intent) {
        // Start the second activity for result - will receive result in onActivityForResult
        startActivityForResult(intent, CHAT_ONE_MESSAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Received event from second activity
        if (requestCode == CHAT_ONE_MESSAGE_REQUEST) { // handle only the specified request code
            if (resultCode == Activity.RESULT_OK && data != null) { // validate the result code & data
                String receivedMessage = data.getStringExtra(EXTRA_INPUT_DATA);
                if (receivedMessage != null) { // Make sure the message is valid
                    onMessageReceived(receivedMessage);
                }
            }
        } // else ignore
    }
}
