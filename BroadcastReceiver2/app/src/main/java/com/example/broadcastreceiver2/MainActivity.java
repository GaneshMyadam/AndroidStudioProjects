package com.example.broadcastreceiver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MyReceiver1 receiver = new MyReceiver1();
    MyReceiver2 receiver2 = new MyReceiver2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter("com.example.EXAMPLE_ACTION");
        filter.setPriority(1);
        registerReceiver(receiver, filter);
        IntentFilter filter1 = new IntentFilter("com.example.EXAMPLE_ACTION");
        filter1.setPriority(2);
        registerReceiver(receiver2, filter1);
    }
    protected void onDestroy () {
            super.onDestroy();
            unregisterReceiver(receiver);
        }
    }