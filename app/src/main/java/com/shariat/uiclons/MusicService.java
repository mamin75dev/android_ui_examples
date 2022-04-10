package com.shariat.uiclons;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {

    private MediaPlayer soundPlayer;
    private String CHANNEL_ID = "channelId";
    private NotificationManager notifManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "service created", Toast.LENGTH_SHORT).show();
        soundPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wind);
        soundPlayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            String offerChannelName = "Service Channel";
            String offerChannelDescription = "Music Channel";
            int offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notifChannel = new NotificationChannel(
                CHANNEL_ID,
                offerChannelName,
                offerChannelImportance
            );
            notifChannel.setDescription(offerChannelDescription);
            notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(notifChannel);
        }

        NotificationCompat.Builder sNotifBuilder =
            new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.music)
            .setContentTitle("music")
            .setContentText("a music played!!!");

        Notification serveNotification = sNotifBuilder.build();
        startForeground(1, serveNotification);

        soundPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service stoped", Toast.LENGTH_SHORT).show();
        soundPlayer.stop();
        super.onDestroy();
    }
}
