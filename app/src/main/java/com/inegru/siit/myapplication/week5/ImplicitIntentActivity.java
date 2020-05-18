package com.inegru.siit.myapplication.week5;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;

import com.inegru.siit.myapplication.R;

public class ImplicitIntentActivity extends AppCompatActivity {

    private static final String TAG = "ImplicitIntentActivity";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 501;

    private EditText inputWebsite;
    private EditText inputLocation;
    private EditText inputShareText;
    private EditText inputCallPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_implicit_intent);

        inputWebsite = findViewById(R.id.etWebsite);
        inputLocation = findViewById(R.id.etLocation);
        inputShareText = findViewById(R.id.etShareText);
        inputCallPhone = findViewById(R.id.etCallPhone);
    }

    public void onOpenWebsite(View view) {
        String url = inputWebsite.getText().toString();
        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void onOpenLocation(View view) {
        String loc = inputLocation.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "Can't handle this intent!");
        }
    }

    public void onShareText(View view) {
        String textToShare = inputShareText.getText().toString();

        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(textToShare)
                .startChooser();
    }

    public void onCallPhone(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                showRationaleDialog(
                        getString(R.string.rationale_title),
                        getString(R.string.rationale_desc),
                        Manifest.permission.CALL_PHONE,
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return;
        }
        // If we reach this line the permission was granted
        String phoneNumber = inputCallPhone.getText().toString();

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Auto-start the flow again
                onCallPhone(null); // comment this to disable the auto-call when permission is allowed
            } else {
                Toast.makeText(this, "Cannot make a phone call without the permission!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void showRationaleDialog(String title, String message, final String permission, final int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(ImplicitIntentActivity.this,
                                new String[]{permission},
                                requestCode);
                    }
                });
        builder.create() // create the dialog
                .show(); // show the dialog
    }
}
