package com.example.aidlserver1;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.IMyAidlInterface;

import java.util.Random;

public class CalService extends Service {
    private static final String TAG = "CalService";
    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }
    private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public int getColor() throws RemoteException {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Log.d(TAG, "getColor: "+ color);
            return color;
        }
    };
}