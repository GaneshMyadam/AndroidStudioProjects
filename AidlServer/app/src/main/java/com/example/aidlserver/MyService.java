package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.IMyAidlInterface;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private MySer ser = new MySer();
    @Override
    public IBinder onBind(Intent intent) {
            return ser;
    }
    private class MySer extends IMyAidlInterface.Stub {
        public int calculate(int num1,int num2) throws RemoteException{
            return num1+num2;
        }
    }
}