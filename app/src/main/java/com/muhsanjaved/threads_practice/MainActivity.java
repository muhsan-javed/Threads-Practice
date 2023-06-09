package com.muhsanjaved.threads_practice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;


public class MainActivity extends AppCompatActivity implements AsyncFragment.MyTaskHandler, LoaderManager.LoaderCallbacks<String> {

    private static final String MESSAGE_KEY = "MESSAGE_KEY";
    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private static final String DATA_KEY = "DATA_KEY";
    Button btnClear, btnRunCode;
    TextView output_text;
    private static final String TAG ="MyTag";
    private ScrollView scrollView;
    private ProgressBar progressBar;
    private Handler mHandler;
    private DownloadThread downloadThread;
//    private MyTask myTask;
    private boolean mTaskRunning;
    private AsyncFragment mAsyncFragment;

    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

       /* FragmentManager manager = getSupportFragmentManager();
        mAsyncFragment = (AsyncFragment) manager.findFragmentByTag(FRAGMENT_TAG);

        if (mAsyncFragment == null){
            mAsyncFragment = new AsyncFragment();
            manager.beginTransaction().add(mAsyncFragment,FRAGMENT_TAG).commit();
        }
*/
        btnClear.setOnClickListener(v -> {
            output_text.setText("");
            progressBar.setVisibility(View.INVISIBLE);
        });

//        output_text.setText(getString(R.string.lorem_ipsum));

      /*  mHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                String data = msg.getData().getString(MESSAGE_KEY);
                Log.d(TAG, "HandleMessage: "+data);
            }
        };

        downloadThread = new DownloadThread(MainActivity.this);
        downloadThread.setName("Download Thread");
        downloadThread.start();*/

        btnRunCode.setOnClickListener(v -> {
//            log("Runner Code");
//            displayProgressBar(true);
          /*   //Send message to download handler
            for (String song: PLayList.songs){
                Message message = Message.obtain();
                message.obj=song;
                downloadThread.downloadHandler.sendMessage(message);
            }*/

          /*  Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            log("Download Comttete");
                            displayProgressBar(false);
                        }
                    });
                }
            });
            thread.start();*/

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

//            log("Runner Code");
//            displayProgressBar(true);


//            MyTask myTask1 =  new MyTask();
//            myTask1.execute("Red", "Green","Blue","Yellow");
         /*   if (mTaskRunning && myTask != null){
                myTask.cancel(true);
                mTaskRunning=false;
            }else {
                myTask =  new MyTask();
                myTask.execute("Red", "Green","Blue","Yellow");
                mTaskRunning =true;
            }*/

//            mAsyncFragment.runTask("Red", "Green","Blue","Yellow");
          /*  for (int i=0; i<10; i++){
                Work work = new Work(i+1);
                executorService.execute(work);
            }*/

            Bundle bundle = new Bundle();
            bundle.putString(DATA_KEY,"some url that returns some daa");

//            getSupportLoaderManager().initLoader(100, bundle,this).forceLoad();
            getSupportLoaderManager().restartLoader(100, bundle,this).forceLoad();
        });

//        executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public void handlerTask(String message) {
            log(message);
    }

   /* class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            for (String value:strings){

                if (isCancelled()){
                    publishProgress("task us cancelled");
                    break;
                }
                Log.d(TAG,"doInBackground: "+value);
                publishProgress(value);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            displayProgressBar(false);
            return "Download Cpmpleted";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            log(values[0]);
        }
        @Override
        protected void onPostExecute(String s) {
            log(s);
        }

        @Override
        protected void onCancelled() {
            log("task has been canelled");
        }
        @Override
        protected void onCancelled(String s) {
            log("Cancelled with return data,:" + s);
        }
    }*/

    public void log(String message) {
        Log.i(TAG, message);
        output_text.append(message + "\n");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {

    }

    public void displayProgressBar(boolean display){
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

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        List<String> songsList = Arrays.asList(PLayList.songs);

        return new MyTaskLoader(this, args,songsList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        log(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    private static class MyTaskLoader extends AsyncTaskLoader<String>
    {
        private Bundle mArgs;
        private List<String> mSongsList;
        public MyTaskLoader(@NonNull Context context, Bundle args, List<String> songsList) {
            super(context);
            this.mArgs=args;
            mSongsList = songsList;
        }

        @Nullable
        @Override
        public String loadInBackground() {
            String data = mArgs.getString(DATA_KEY);

            Log.d(TAG, "loadInBackground: URL:"+ data);
            Log.d(TAG, "loadInBackground:  Thread name: "+ Thread.currentThread().getName());

            for (String song:mSongsList){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"loadInBackground: Song Name: "+song);
            }

            Log.d(TAG, "loadInBackground:  Thread Terminated:: ");
            return "\n Result from Loader";
        }

        @Override
        public void deliverResult(@Nullable String data) {
            data += " :Modified";
            super.deliverResult(data);
        }
    }
}