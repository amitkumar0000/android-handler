package com.android.handler.Activityhandler;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.handler.MainActivity;

public class MainHandler extends Handler{
    final String TAG = "MainHandler";
    Context context;
    TextView textView;



    public MainHandler(MainActivity mainActivity, TextView viewById) {
        this.textView = viewById;
        this.context = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 1:{
                if(Looper.myLooper() == Looper.getMainLooper()) {
                   printOnUIThread("handleMessage in main thread");
                    Log.d(TAG, "handleMessage in main thread " + msg.what +" "+ Looper.getMainLooper());
                }else{
                   printOnUIThread("handleMessage in background thread");
                    Log.d(TAG, "handleMessage in background thread " + msg.what + Looper.myLooper());
                }
                break;
            }
        }
    }

    void printOnUIThread(final String text){
        final Activity activity = (Activity)context;
        (activity).runOnUiThread(new Runnable() {
            public void run() {
                textView.setText(text);
            }
        });
    }
}
