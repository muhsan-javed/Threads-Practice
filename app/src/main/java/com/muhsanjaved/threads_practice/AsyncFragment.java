package com.muhsanjaved.threads_practice;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AsyncFragment extends Fragment {

    private static final String TAG = "MyTag";
    private MyTaskHandler mTaskHandler;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public interface MyTaskHandler{
        void handlerTask(String message);
    }

    public void onAttach(Context context){
        super.onAttach(context);

        Log.d(TAG,"onAttach: fragment attached");
        if (context instanceof MyTaskHandler){
            mTaskHandler = (MyTaskHandler) context;
        }
    }

    public void runTask(String... params){
        MyTask myTask =  new MyTask();
        myTask.execute(params);
    }
    class MyTask extends AsyncTask<String,String,String> {

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
//            displayProgressBar(false);
            return "Download Cpmpleted";
        }

        @Override
        protected void onProgressUpdate(String... values) {
//            log(values[0]);
            mTaskHandler.handlerTask(values[0]);
        }
        @Override
        protected void onPostExecute(String s) {
            mTaskHandler.handlerTask(s);
        }

        @Override
        protected void onCancelled() {
//            log("task has been canelled");
        }
        @Override
        protected void onCancelled(String s) {
//            log("Cancelled with return data,:" + s);
        }
    }
}
