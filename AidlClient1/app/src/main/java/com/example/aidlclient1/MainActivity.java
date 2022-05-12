package com.example.aidlclient1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aidl.IMyAidlInterface;

import java.util.List;

public class MainActivity extends Activity {
    protected IMyAidlInterface calService;
    private ServiceConnection connection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.aidlserver1", "com.example.aidlserver1.CalService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Button b = findViewById(R.id.button);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                calService = IMyAidlInterface.Stub.asInterface(service);
                Toast.makeText(getApplicationContext(), "Service Connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                calService = null;
                Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
            }
        };
    }


                public void changeColor(View view) {
                    try {
                        int color = calService.getColor();
                        view.setBackgroundColor(color);
                        Toast.makeText(MainActivity.this, "color changed", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                    }
                }
            }