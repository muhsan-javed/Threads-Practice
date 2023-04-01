package com.muhsanjaved.threads_practice;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class DownloadHandler extends Handler {
    private static final String TAG ="MyTag";

    @Override
    public void handleMessage(@NonNull Message msg) {
       downloadSong(msg.obj.toString());
    }

    private void downloadSong(String songName){
        Log.d(TAG, "run: starting download");
        try {
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d(TAG,"download Song: "+songName+" Downloaded.....");
    }
}
