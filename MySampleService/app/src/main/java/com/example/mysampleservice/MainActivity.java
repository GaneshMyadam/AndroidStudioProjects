package com.example.mysampleservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter=0;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main Thread", "Main Thread id:" + Thread.currentThread().getId());
        serviceIntent = new Intent(getApplicationContext(), MyService.class);
    }
    public void countButton(View v){
        counter++;
        TextView textView = findViewById(R.id.textView);
        textView.setText(Integer.toString(counter));
    }
    public void startButton(View v){
        //Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(serviceIntent);
    }
    public void stopButton(View v){
        stopService(serviceIntent);

    }
}