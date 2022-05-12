package com.example.myservices1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {

    private String primeNumber;
    private boolean primeGeneratorOn;

    class MyServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    private final IBinder mBinder=new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Service Start","In OnBind");
            return mBinder;
        }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("Service Start","In OnReBind");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPrimeNumberGenerator();
        Log.i("Service Start","Service Destroyed");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service Start","In onStartCommend, thread id: "+Thread.currentThread().getId());
        primeGeneratorOn =true;
        new Thread(() -> startPrimeNumberGenerator()).start();
        return START_STICKY;
    }

    private void startPrimeNumberGenerator() {

        while (primeGeneratorOn) {
            try {
               // Thread.sleep(1000);
                if (primeGeneratorOn) {
                    int a[]= new int[100];
                    for (int i = 2; i <=a.length; i++)
                    {
                        boolean isPrime= true;
                        for(int num =2; num<i; num++)
                        {
                            if(i%num==0)
                            {
                               isPrime = false;
                               break;
                            }
                        }
                        if (isPrime)
                        {
                            primeNumber = String.valueOf(i);
                            Log.i("Service Start", "Thread id: " + Thread.currentThread().getId() + ", Prime Number: " + primeNumber);
                            Thread.sleep(1000);
                        }
                    }
                }

            } catch (InterruptedException e) {
                Log.i("Service Start", "Thread Interrupted");
            }
        }
    }

    private void stopPrimeNumberGenerator(){
        primeGeneratorOn =false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Service","In onUnbind");
        return super.onUnbind(intent);
    }

    public String getPrimeNumber(){
        return primeNumber;
    }
}