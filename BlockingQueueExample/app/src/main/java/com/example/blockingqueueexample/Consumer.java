package com.example.blockingqueueexample;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Log.i("Lol", "run: "+queue.take());
            Log.i("Lol", "run: "+queue.take());
            Log.i("Lol", "run: "+queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
