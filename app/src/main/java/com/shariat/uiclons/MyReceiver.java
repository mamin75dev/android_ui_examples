package com.shariat.uiclons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.i("intent_action", intent.getAction());
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
            if (NetworkService.checkNetworkConnection(context))
                Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "internet not connected", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Toast.makeText(context, "Sms received", Toast.LENGTH_SHORT).show();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
            }
        }

    }
}
