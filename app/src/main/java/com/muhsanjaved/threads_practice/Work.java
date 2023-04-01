package com.muhsanjaved.threads_practice;

import android.util.Log;

public class Work implements Runnable{
    private static final String TAG = "MyTag";
    private int mThreadNo;

    public Work(int mThreadNo) {
        this.mThreadNo = mThreadNo;
    }

    @Override
    public void run() {
        Log.d(TAG, "run: "+Thread.currentThread().getName() +" start, thread no: "+mThreadNo);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "run: "+Thread.currentThread().getName() +" end, thread no: "+mThreadNo);
    }
}
