package com.example.voiceservicebot.rough;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.voiceservicebot.MainActivity;
import com.example.voiceservicebot.R;

import static com.example.voiceservicebot.MainActivity.CHANNEL_ID;
import static com.example.voiceservicebot.MainActivity.MUTE;

public class BroadcastClass extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManagerCompat = NotificationManagerCompat.from(context);
        String msg = intent.getStringExtra("toastMessage");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


        if(MUTE == 1){
            //Its now listening, so mute it and set text as unmute
            StartCustomNotifyUNMUTE(context);
        }else{
            //Its now not listening, so unmute it and set text as mute
            StartCustomNotify(context);
        }


    }

    private void StartCustomNotifyUNMUTE(Context context) {

        Intent acticityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,acticityIntent,0);

        Intent broadcastIntent = new Intent(context,BroadcastClass.class);
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
                .addAction(R.mipmap.ic_launcher,"UNMUTE",actionIntent)
                .build();

        MUTE = 0;

        notificationManagerCompat.notify(1,notification);
    }

    public void StartCustomNotify(Context context) {

        Intent acticityIntent = new Intent(context,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,acticityIntent,0);

        Intent broadcastIntent = new Intent(context,BroadcastClass.class);
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
}
