package com.example.kobewang.myapplication;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kobewang on 2017/6/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("kobe123","MyApplication-->onCreate");
        Intent intent = new Intent(this,MyService.class);
        this.startService(intent);
    }
}
