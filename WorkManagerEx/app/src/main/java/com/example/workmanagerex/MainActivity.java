package com.example.workmanagerex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private Button StartWorkbtn,StopWorkbtn;
    private TextView textView;
    int count = 0;
    WorkManager workManager;
    private boolean mStopLoop;
    private WorkRequest workRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"MainActivity Thread id"+Thread.currentThread().getId());
        StartWorkbtn = findViewById(R.id.StartWorkbtn);
        StopWorkbtn = findViewById(R.id.StopWorkbtn);
//      textView = findViewById(R.id.textView);

        StartWorkbtn.setOnClickListener(this);
        StopWorkbtn.setOnClickListener(this);
        workManager = WorkManager.getInstance(getApplicationContext());
//        workRequest = new OneTimeWorkRequest.Builder(RandomNumberGeneratorWorker.class).build();
        workRequest =new  PeriodicWorkRequest.Builder(RandomNumberGeneratorWorker.class,15, TimeUnit.MILLISECONDS).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.StartWorkbtn:
                mStopLoop = true;
                workManager.enqueue(workRequest);
                break;
            case R.id.StopWorkbtn:
                workManager.cancelWorkById(workRequest.getId());
                break;
        }
    }
}