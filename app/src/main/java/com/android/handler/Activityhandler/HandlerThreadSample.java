package com.android.handler.Activityhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.android.handler.MainActivity;

import static com.android.handler.utils.Contants.MSG;
import static com.android.handler.utils.Contants.MSG_2;

public class HandlerThreadSample extends HandlerThread{
    final String TAG = "Activity_HanderThread";


    Handler handler;
    Handler mainHandler;
    public HandlerThreadSample(String name, Handler mainHandler) {
        super(name);
        this.mainHandler = mainHandler;
    }


    @Override
    public void run() {
        super.run();

    }

    public void sendMsgToMainThread(String msg){
        Message message = Message.obtain();
        message.what = 1;
        Bundle bundle = new Bundle();

        message.setData(bundle);
        handler.sendMessage(message);
    }


    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:{
                        Message message = Message.obtain();
                        message.what = MSG_2;
                        Bundle bundle =     msg.getData();
                        String bundleMsg = "";
                        if(Looper.myLooper() == Looper.getMainLooper()) {
                            bundleMsg = "Main Thread";
                        }else{
                            bundleMsg = "Background Thread";
                        }
                        bundle.putString(MSG,bundleMsg);

                        message.setData(bundle);
                        mainHandler.sendMessage(message);
                        break;
                    }
                }
            }
        };
    }
}
