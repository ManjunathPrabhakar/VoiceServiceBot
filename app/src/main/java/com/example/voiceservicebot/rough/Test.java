package com.example.voiceservicebot.rough;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.voiceservicebot.MainActivity;
import com.example.voiceservicebot.R;

public class Test {
    public static int MUTE = 0;
    public static final String CHANNEL_ID = "notifyChannel";
    private NotificationManagerCompat notificationManagerCompat;

    public Test(Context context){
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    public void StartCustomNotify(Context context) {

        Intent acticityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,acticityIntent,0);

        Intent broadcastIntent = new Intent(context, BroadcastClass.class);
        broadcastIntent.putExtra("toastMessage","some message");
        PendingIntent actionIntent = PendingIntent.getBroadcast(context,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);



        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("SOME Service")
                .setContentText("input")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher,"MUTE",actionIntent)
                .build();

        MUTE = 1;

        notificationManagerCompat.notify(1,notification);
    }



    private void triggerCustomNotify(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notify Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }

    }
}
