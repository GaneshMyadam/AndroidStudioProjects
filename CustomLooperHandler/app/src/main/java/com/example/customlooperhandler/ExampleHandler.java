package com.example.customlooperhandler;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class ExampleHandler extends Handler {
    private static final String TAG = "ExampleHandler";

    public static final int TASK_A = 1;
    public static final int TASK_B = 2;

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case TASK_A:

                post(() -> {
                    for (int i = 0; i < 10; i++) {
                        Log.d(TAG, "TASK A: " + i);
                        SystemClock.sleep(1000);
                    }
                });
                Log.d(TAG, "Task A executing");
                break;
            case TASK_B:
                post(() -> {
                    for (int i = 0; i < 10; i++) {
                        Log.d(TAG, "TASK A: " + i);
                        SystemClock.sleep(1000);
                    }
                });
                Log.d(TAG, "Task B executing");
                break;
        }
    }
}
