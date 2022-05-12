package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidl.IMyAidlInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IMyAidlInterface intr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "Service Connected");
                intr = IMyAidlInterface.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "Service Disconnected");
                intr = null;
            }
        };

        findViewById(R.id.bindbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick Called");
                Intent explicit = new Intent();
                explicit.setComponent(new ComponentName("com.example.aidlserver","com.example.aidlserver.MyService"));
                bindService(explicit,serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.calcbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Log.i(TAG, "Addition is:" + intr.calculate(10, 20));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}