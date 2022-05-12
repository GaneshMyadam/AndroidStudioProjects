package com.example.myserverapk;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import java.util.Random;

public class MyService extends Service {

    private static final String TAG=MyService.class.getSimpleName();

    private int myRandomNumber;
    private boolean myRandomGeneratorOn;

    public static final int GET_RANDOM_NUMBER_FLAG=0;

    private class MyRandomNumberRequestHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case GET_RANDOM_NUMBER_FLAG:
                    Message  messageSendRandomNumber=Message.obtain(null, GET_RANDOM_NUMBER_FLAG);
                    messageSendRandomNumber.arg1=getRandomNumber();
                    try{
                        msg.replyTo.send(messageSendRandomNumber);
                    }catch (RemoteException e){
                        Log.i(TAG,""+e.getMessage());
                    }
            }
            super.handleMessage(msg);
        }
    }

    private Messenger randomNumberMessenger=new Messenger(new MyRandomNumberRequestHandler());


    @Override
    public IBinder onBind(Intent intent) {
        return randomNumberMessenger.getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumberGenerator();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myRandomGeneratorOn =true;
        new Thread(() -> startRandomNumberGenerator()).start();
        return START_STICKY;
    }

    private void startRandomNumberGenerator(){
        while (myRandomGeneratorOn){
            try{
                Thread.sleep(1000);
                if(myRandomGeneratorOn){
                    int MIN = 0;
                    int MAX = 100;
                    myRandomNumber =new Random().nextInt(MAX)+ MIN;
                    Log.i(TAG,"Random Number is : "+myRandomNumber);
                }
            }catch (InterruptedException e){
                Log.i(TAG,"Thread Interrupted");
            }

        }
    }

    private void stopRandomNumberGenerator(){
        myRandomGeneratorOn =false;
        Toast.makeText(getApplicationContext(),"Service Stopped",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

    public int getRandomNumber(){
        return myRandomNumber;
    }


}