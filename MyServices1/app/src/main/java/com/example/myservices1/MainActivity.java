package com.example.myservices1;
import static android.content.ContentValues.TAG;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    public Button buttonStop;
    public Button buttonBind;
    public Button buttonUnBind;
    public Button buttonGetRandomNumber;
    public TextView textViewthreadCount;
    static int count = 0;
   private MyAsyncTask myAsyncTask;

    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection  serviceConnection;
    private  Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "MainActivity thread id: " + Thread.currentThread().getId());

        Button buttonStart = findViewById(R.id.buttonThreadStarter);
        buttonStop = findViewById(R.id.buttonStopthread);
        buttonBind=findViewById(R.id.buttonBindService);
        buttonUnBind=findViewById(R.id.buttonUnBindService);
        buttonGetRandomNumber=findViewById(R.id.buttonGetRandomNumber);


        textViewthreadCount = findViewById(R.id.textViewthreadCount);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonBind.setOnClickListener(this);
        buttonUnBind.setOnClickListener(this);
        buttonGetRandomNumber.setOnClickListener(this);
        serviceIntent=new Intent(getApplicationContext(),MyService.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonThreadStarter:
                boolean mStopLoop = true;
                startService(serviceIntent);
                break;
            case R.id.buttonStopthread:
                stopService(serviceIntent);
                break;
            case R.id.buttonBindService:
                bindService();
                break;
            case R.id.buttonUnBindService:
                unbindService();
                break;
            case R.id.buttonGetRandomNumber:
                setPrimeNumber();
                break;
        }
    }
    private void bindService(){
        if(serviceConnection==null){
            serviceConnection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    MyService.MyServiceBinder myServiceBinder=(MyService.MyServiceBinder)iBinder;
                    myService=myServiceBinder.getService();
                    isServiceBound=true;
                }
                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound=false;
                }
            };
        }

        bindService(serviceIntent,serviceConnection,Context.BIND_AUTO_CREATE);

    }

    private void unbindService(){
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound=false;
        }
    }

    private void setPrimeNumber(){
        if(isServiceBound){
            textViewthreadCount.setText("Random Prime number: "+myService.getPrimeNumber());
        }else{
            textViewthreadCount.setText("Service not bound");
        }
    }

    private static class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        private int customCounter;
        private BreakIterator textViewthreadCount;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customCounter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            customCounter = integers[0];
            boolean mStopLoop = true;
            while (mStopLoop) {
                try {
                    Thread.sleep(1000);
                    customCounter++;
                    publishProgress(customCounter);
                } catch (InterruptedException e) {
                    Log.i(TAG, e.getMessage());
                }

            }
            return customCounter;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewthreadCount.setText(""+values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textViewthreadCount.setText(""+integer);
            count=integer;
        }
    }
}