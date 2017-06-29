package com.example.kobewang.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ff");
        Toast tt = Toast.makeText(this,"你好",Toast.LENGTH_LONG);
        //View view = tt.getView();
        //view.setBackgroundColor(Color.RED);
        //tt.setView(view);
        tt.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
