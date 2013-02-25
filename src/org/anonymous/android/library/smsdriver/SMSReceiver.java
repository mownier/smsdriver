package org.anonymous.android.library.smsdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive : Received message.");
		if (context != null && intent != null) {
			Bundle bundle = intent.getExtras();
			if(bundle != null && bundle.size() > 0) {
				String str = "";
				Object[] pdus = (Object[]) bundle.get("pdus");

				SmsMessage[] msgs = new SmsMessage[pdus.length];
				for (int i = 0; i < msgs.length; i++) {
					msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
					str += "SMS from " + msgs[i].getOriginatingAddress();
					str += " :";
					str += msgs[i].getMessageBody().toString();
					str += "\n";
				}
				Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
			}
		} 

	}

}
