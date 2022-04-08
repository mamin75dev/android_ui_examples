package com.shariat.uiclons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyReceiver receiver;
    private final int SMS_REQUEST_CODE = 100;

    private TextView smsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsContent = findViewById(R.id.sms_content);

        if (
            ContextCompat.checkSelfPermission(
                MainActivity.this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestSMSPermission();
        } else {
            Toast.makeText(this, "already granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestSMSPermission() {
        ActivityCompat.requestPermissions(
            MainActivity.this,
            new String[]{Manifest.permission.RECEIVE_SMS},
            SMS_REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "permission blocked", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    protected void onResume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            receiver = new MyReceiver();
            registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }
}