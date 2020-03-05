package com.example.voiceservicebot;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.voiceservicebot.MainActivity.CHANNEL_ID;
import static com.example.voiceservicebot.MainActivity.MUTE;
import static com.example.voiceservicebot.services.VoiceService.CHANNEL_ID_S;
import static com.example.voiceservicebot.services.VoiceService.MUTE_S;

public class BroadcastServiceClass extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManagerCompat = NotificationManagerCompat.from(context);
        String msg = intent.getStringExtra("toastMessage");
        //Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


        if (MUTE_S == 1) {
            //Its now listening, so mute it and set text as unmute
            unMuteNoti(context);
            Intent i = new Intent();
            i.setClassName(context.getPackageName(), STT.class.getName());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            //Its now not listening, so unmute it and set text as mute
            muteNoti(context);
        }


    }

    private void unMuteNoti(Context context) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, notificationIntent, 0);

        ////////////////////////
        Intent broadcastIntent = new Intent(context, BroadcastServiceClass.class);
        broadcastIntent.putExtra("toastMessage", "some message");
        PendingIntent actionIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        MUTE_S = 0;
        ////////////////////////

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID_S)
                .setContentTitle("Foreground Service")
                .setContentText("input")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher_round, "UNMUTE", actionIntent)
                .build();

        notificationManagerCompat.notify(1,notification);
    }

    private void muteNoti(Context context) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, notificationIntent, 0);

        ////////////////////////
        Intent broadcastIntent = new Intent(context, BroadcastServiceClass.class);
        broadcastIntent.putExtra("toastMessage", "some message");
        PendingIntent actionIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        MUTE_S = 1;
        ////////////////////////

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID_S)
                .setContentTitle("Foreground Service")
                .setContentText("input")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher_round, "MUTE", actionIntent)
                .build();

        notificationManagerCompat.notify(1,notification);
    }
}
