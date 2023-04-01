package com.muhsanjaved.threads_practice;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class DownloadThread extends Thread{
    private static final String TAG = "MyTag";
    private final MainActivity mActivity;
    public DownloadHandler downloadHandler;

    public DownloadThread(MainActivity activity){
        this.mActivity=activity;
    }

    //    private final String songName;
//
//    public DownloadThread(String songName) {
//        this.songName = songName;
//    }
    @Override
    public void run() {
        Looper.prepare();
        downloadHandler = new DownloadHandler(mActivity);
        Looper.loop();

        //        for (String song:PLayList.songs){
//            downloadSong(song);
//        }
    }

  /*  private void downloadSong(String songName){
        Log.d(TAG, "run: starting download");
        try {
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d(TAG,"download Song: "+songName+" Downloaded.....");

    }*/
}
