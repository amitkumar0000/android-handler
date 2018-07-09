package com.android.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.handler.Activityhandler.Activity_Hander;

public class MainActivity extends AppCompatActivity {

    Activity_Hander activity_hander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_hander = new Activity_Hander();
        activity_hander.sendEmptyMessage(1);
    }
}
