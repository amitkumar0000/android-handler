package com.android.handler.Activityhandler;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import static com.android.handler.utils.Contants.MSG;
import static com.android.handler.utils.Contants.MSG_1;


public class ThreadSample extends Thread{
    final String TAG = "Activity_Thread";


    Handler mainHandler;
    Context context;
    MyThreadHandler threadHandler;

    public ThreadSample(Context context, Handler mainHandler){
        this.context = context;
        this.mainHandler = mainHandler;
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        threadHandler = new MyThreadHandler();
        Looper.loop();
    }

    public void sendMsgToMainThread(String msg){
        Message message = Message.obtain();
        message.what = 1;
        Bundle bundle = new Bundle();
        if(Looper.myLooper() == Looper.getMainLooper()) {
            msg = "Main Thread";
        }else{
            msg = "Background Thread";
        }

        bundle.putString(MSG,msg);
        message.setData(bundle);
        threadHandler.sendMessage(message);
    }

    public void stopLooper(){
        Looper.myLooper().quit();
    }


    class MyThreadHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{
                    Message message = Message.obtain();
                    message.what = MSG_1;
                    Bundle bundle =    msg.getData();
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
    }
}
