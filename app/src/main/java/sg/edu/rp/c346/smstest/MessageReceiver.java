package sg.edu.rp.c346.smstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //to parse the SMS message received and display the sender’s number as well as the message in a Toast
        // SMS messages are retrieved from the intent's extra using the key "pdus"
        Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage currentMessage;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = bundle.getString("format");
                    currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0], format);
                } else {
                    currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
                }
                // Obtain the originating phone number (sender's number)
                String senderNum = currentMessage.getDisplayOriginatingAddress();
                // Obtain the message body
                String message = currentMessage.getDisplayMessageBody();
                // Display in Toast
                Toast.makeText(context, "Sender Number: " + senderNum +
                        ", Message: " + message, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Error: " + e);
        }

    }
}