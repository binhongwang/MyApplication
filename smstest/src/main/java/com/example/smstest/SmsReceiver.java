package com.example.smstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by kobewang on 2017/6/21.
 */

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");

        String mobile = "";
        StringBuffer message = new StringBuffer();

        for (Object pdu : pdus) {
            //获取短信
            byte[] pdusmessage = (byte[]) pdu;
            SmsMessage sms = SmsMessage.createFromPdu(pdusmessage);
            mobile = sms.getOriginatingAddress();
            message.append(sms.getMessageBody());
        }

        Toast.makeText(context, "短信被拦截,内容是:" + message + "，发送者:" + mobile, Toast.LENGTH_SHORT).show();
    }
}
