package com.example.foregroundapp;

import static com.example.foregroundapp.MyApplication.channelId;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Random;

public class MyService extends Service {
    private int randomNumber;
    private boolean randomGeneratorOn;
    private final int MIN=0;
    private final int MAX=100;
    private static final String TAG = "MyService";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Example Service")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
       // randomGeneratorOn= true;
       // randomNumberGenerator();
        return START_NOT_STICKY;
    }

   /* private void randomNumberGenerator(){
        while (randomGeneratorOn){
            try{
                Thread.sleep(1000);
                if(randomGeneratorOn){
                    randomNumber =new Random().nextInt(MAX)+MIN;
                    Log.i(TAG,"Thread id: "+Thread.currentThread().getId()+", Random Number: "+ randomNumber);
                }
            }catch (InterruptedException e){
                Log.i(TAG,"Thread Interrupted");
            }
        }
    }*/
    @Override
    public void onDestroy() {
        super.onDestroy();
      //  randomGeneratorOn=false;
       // Log.i(TAG, ", thread Id: "+Thread.currentThread().getId());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}