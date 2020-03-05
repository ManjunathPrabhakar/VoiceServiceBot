package com.example.voiceservicebot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.voiceservicebot.rough.BroadcastClass;
import com.example.voiceservicebot.services.VoiceService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int MUTE = 0;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public static TextView tv;

    Button btnStartService, btnStopService, btnCustomNotify;
    public static final String CHANNEL_ID = "notifyChannel";
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartService = findViewById(R.id.buttonStartService);
        btnStopService = findViewById(R.id.buttonStopService);
        btnCustomNotify = findViewById(R.id.customNotify);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        tv = findViewById(R.id.voicedata);


    }


    public void startService() {
        Intent serviceIntent = new Intent(this, VoiceService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, VoiceService.class);
        stopService(serviceIntent);
    }

    public static void setText(String data){
        tv.setText(data);
    }

}