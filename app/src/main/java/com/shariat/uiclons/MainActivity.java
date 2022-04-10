package com.shariat.uiclons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyReceiver receiver;
    private final int SMS_REQUEST_CODE = 100;

//    private TextView smsContent;
    private Button startButton, stopButton, navigateButton;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        smsContent = findViewById(R.id.sms_content);
//
//        if (
//            ContextCompat.checkSelfPermission(
//                MainActivity.this,
//                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
//        ) {
//            requestSMSPermission();
//        } else {
//            Toast.makeText(this, "already granted", Toast.LENGTH_SHORT).show();
//        }

        startButton = findViewById(R.id.start_btn);
        stopButton = findViewById(R.id.stop_btn);
        navigateButton = findViewById(R.id.two_btn);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        navigateButton.setOnClickListener(this);

        serviceIntent = new Intent(this, MusicService.class);

    }

//    private void requestSMSPermission() {
//        ActivityCompat.requestPermissions(
//            MainActivity.this,
//            new String[]{Manifest.permission.RECEIVE_SMS},
//            SMS_REQUEST_CODE
//        );
//    }
//
//    @Override
//    public void onRequestPermissionsResult(
//        int requestCode,
//        @NonNull String[] permissions,
//        @NonNull int[] grantResults
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == SMS_REQUEST_CODE) {
//
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
//
//            } else {
//
//                Toast.makeText(this, "permission blocked", Toast.LENGTH_SHORT).show();
//
//            }
//
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            receiver = new MyReceiver();
//            registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
//            registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
//        }
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        unregisterReceiver(receiver);
//        super.onPause();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn: {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    startForegroundService(serviceIntent);
//                } else {
//                    startService(serviceIntent);
//                }
                ContextCompat.startForegroundService(MainActivity.this, serviceIntent);
                break;
            }
            case R.id.stop_btn: {
                stopService(serviceIntent);
                break;
            }
            case R.id.two_btn: {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                break;
            }
        }
    }
}