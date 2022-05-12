package com.example.handlernlooper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button buttonStart, buttonStop;
    private TextView textViewthreadCount;
    int count = 0;
    private Intent serviceIntent;

    private boolean mStopLoop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "MainActivity thread id: " + Thread.currentThread().getId());

        buttonStart = (Button) findViewById(R.id.buttonThreadStarter);
        buttonStop = (Button) findViewById(R.id.buttonStopthread);


        textViewthreadCount = (TextView) findViewById(R.id.textViewthreadCount);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        Handler handler = new Handler(getApplicationContext().getMainLooper());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonThreadStarter:
                mStopLoop = true;

                new Thread(() -> {
                    while (mStopLoop) {
                        try {
                            Thread.sleep(1000);
                            count++;
                        } catch (InterruptedException e) {
                            Log.i(TAG, e.getMessage());
                        }
                        Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId() + ", Count : " + count);
                        textViewthreadCount.post(() -> textViewthreadCount.setText(" " + count));
                    }
                }).start();
                break;
        case R.id.buttonStopthread :
            mStopLoop=false;
            break;
    }
    }
}