package com.muhsanjaved.threads_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_KEY = "MESSAGE_KEY";
    Button btnClear, btnRunCode;
    TextView output_text;
    private static final String TAG ="MyTag";
    private ScrollView scrollView;
    private ProgressBar progressBar;
    private Handler mHandler;
    DownloadThread downloadThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnClear.setOnClickListener(v -> {
            output_text.setText("");
            progressBar.setVisibility(View.INVISIBLE);
        });

//        output_text.setText(getString(R.string.lorem_ipsum));

       /* mHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                String data = msg.getData().getString(MESSAGE_KEY);
                Log.d(TAG, "HandleMessage: "+data);
            }
        };*/

        downloadThread = new DownloadThread();
        downloadThread.setName("Download Thread");
        downloadThread.start();

        btnRunCode.setOnClickListener(v -> {

            log("Runner Code");
            displayProgressBar(true);

            //Send message to download handler
            for (String song: PLayList.songs){
                Message message = Message.obtain();
                message.obj=song;
                downloadThread.handler.sendMessage(message);
            }

//            for (String song:PLayList.songs){
//                DownloadThread thread = new DownloadThread();
//                thread.setName("Download Thread");
//                thread.start();
//            }

//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    Log.d(TAG, "run starting download");
//                    try {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    Message message = new Message();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(MESSAGE_KEY,"Download Completed");
//                    message.setData(bundle);
//
//                    mHandler.sendMessage(message);
//
////                    Log.d(TAG, Thread.currentThread().getName());
////                    Log.d(TAG, "run Download completed");
////                    displayProgressBar(false);
//
//                }
//            };
//
//            Handler handler = new Handler();
//            handler.postDelayed(runnable, 4000);
//            Thread thread = new Thread(runnable);
//            thread.setName("Download Thread");
//            thread.start();
        });
    }

    private void log(String message) {
        Log.i(TAG, message);
        output_text.append(message + "\n");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {

    }

    private void displayProgressBar(boolean display){
        if (display)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    private void initViews() {
        btnClear = findViewById(R.id.btnClear);
        btnRunCode = findViewById(R.id.btnRunCode);
        output_text = findViewById(R.id.output_text);
        progressBar = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollViewId);
    }
}