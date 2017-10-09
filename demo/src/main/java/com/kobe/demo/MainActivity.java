package com.kobe.demo;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
        setContentView(R.layout.activity_main);
        WifiManager wm = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
        Log.v("kobe", "m_szWLANMAC->" + m_szWLANMAC);
        test();
    }

    private void test() {

    }
}
