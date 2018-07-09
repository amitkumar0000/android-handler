package com.android.handler.Activityhandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class Activity_Hander extends Handler{
    final String TAG = "Activity_Hander";

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 1:{
                if(Looper.myLooper() == Looper.getMainLooper()) {
                    Log.d(TAG, "handleMessage in main thread " + msg.what + this.getLooper());
                }else{
                    Log.d(TAG, "handleMessage in background thread " + msg.what + this.getLooper());
                }
                break;
            }
        }
    }
}
