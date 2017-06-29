package com.example.smstest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTxt;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxt = (TextView) findViewById(R.id.txt);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    private Uri SMS_INBOX = Uri.parse("content://sms/");

    public String getSmsFromPhone() {
        StringBuffer sb = new StringBuffer();
        ContentResolver cr = getContentResolver();
        String[] projection = new String[]{"body", "address"};//"_id", "address", "person",, "date", "type
        Cursor cur = cr.query(SMS_INBOX, projection, null, null, "date desc");
        if (null == cur)
            return "";
        while (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String body = cur.getString(cur.getColumnIndex("body"));//短信内容
            sb.append("发信人：" + number + "\n");
            sb.append("内容：" + body + "\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void onClick(View view) {
        mTxt.setText(getSmsFromPhone());
    }
}
