package com.android.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.handler.Activityhandler.HandlerThreadSample;
import com.android.handler.Activityhandler.ThreadSample;
import com.android.handler.utils.Contants;

import static com.android.handler.utils.Contants.MSG_1;
import static com.android.handler.utils.Contants.MSG_2;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "ActivityHandler";
    com.android.handler.Activityhandler.MainHandler mainHandler;
    MainHandler handler;
    ThreadSample threadSample;
    static TextView textView1;
    static TextView textView2;
    static TextView textView3;
    HandlerThreadSample handler_threadClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);

    }

    @Override
    protected void onStart() {
        super.onStart();
        handler = new MainHandler();
        threadSample = new ThreadSample(this,handler);
        threadSample.start();

        handler_threadClass = new HandlerThreadSample("handlerThread",handler);
        handler_threadClass.start();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button1:{
                mainHandler = new com.android.handler.Activityhandler.MainHandler(this, textView1);
                mainHandler.sendEmptyMessageDelayed(1,1*1000);
                break;
            }
            case R.id.button2:{
                threadSample.sendMsgToMainThread(String.valueOf(textView1.getText()));
                break;
            }
            case R.id.button3:{
                handler_threadClass.sendMsgToMainThread(String.valueOf(textView1.getText()));
                break;
            }
        }
    }

    static class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_1:{
                    Bundle bundle = msg.getData();
                    Log.d(TAG,"Message received from Count Thread 1");
                    textView2.setText(bundle.getString(Contants.MSG));
                    break;
                }
                case MSG_2:{
                    Bundle bundle = msg.getData();
                    Log.d(TAG,"Message received from Count Thread 2");
                    textView3.setText(bundle.getString(Contants.MSG));
                    break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadSample.stopLooper();
        handler_threadClass.quit();
    }
}
