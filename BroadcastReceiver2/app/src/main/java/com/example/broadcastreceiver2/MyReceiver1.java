package com.example.broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "OBR1 Triggered", Toast.LENGTH_SHORT).show();
        Log.i("OR1","OR1 Created");
    }
}
