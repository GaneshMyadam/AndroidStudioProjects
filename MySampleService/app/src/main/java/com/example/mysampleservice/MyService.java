package com.example.mysampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    @Override
    public void onCreate() {
        Log.i("Service Start", "Service Created");
        super.onCreate();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable runnable = () -> {
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){

                }
                Log.i("Service Start","Task completed:::" +Integer.toString(i));
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("Service Started", "Service Destroyed");
        super.onDestroy();
    }
}