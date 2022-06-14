package com.example.workmanagerex;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.Random;

public class RandomNumberGeneratorWorker extends Worker {
    Context context;
    WorkerParameters workerParameters;
    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    public RandomNumberGeneratorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        this.workerParameters = workerParams;
        mIsRandomGeneratorOn = true;
    }

    @NonNull
    @Override
    public Result doWork() {
        startRandomNumberGenerator();
        return Result.success();
    }
    public void onStopped(){
        Log.i("Random Number" ,"Worker has been cancelled");
    }


    private void startRandomNumberGenerator() {
        int i = 0;
        while (i < 5 && !isStopped()) {
            try {
                Thread.sleep(1000);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i("Random Number", "Thread id: " + Thread.currentThread().getId() + ", Random Number: " + mRandomNumber);
                    i++;
                }
            } catch (InterruptedException e) {
                Log.i("Error", "Thread Interrupted");
            }
        }
    }
}
