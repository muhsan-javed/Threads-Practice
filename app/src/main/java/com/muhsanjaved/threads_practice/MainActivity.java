package com.muhsanjaved.threads_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btnClear, btnRunCode;
    TextView output_text;
    private static final String TAG ="MyTag";
    private ScrollView scrollView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnClear.setOnClickListener(v -> output_text.setText(""));

//        output_text.setText(getString(R.string.lorem_ipsum));

        btnRunCode.setOnClickListener(v -> {

            log("Runner Code");
            displayProgressBar(true);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run starting download");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, Thread.currentThread().getName());
                    Log.d(TAG, "run Download completed");
//                    displayProgressBar(false);

                }
            };

//            Handler handler = new Handler();
//            handler.postDelayed(runnable, 4000);
            Thread thread = new Thread(runnable);
            thread.setName("Download Thread");
            thread.start();
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