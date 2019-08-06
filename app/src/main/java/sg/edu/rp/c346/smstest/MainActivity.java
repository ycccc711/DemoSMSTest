package sg.edu.rp.c346.smstest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.button);

        checkPermission();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send sms
                SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage("phoneNo", null, "Message content", null, null);

                //send a message “Hello World!” to number 5554
                smsManager.sendTextMessage("5554", null, "Hello World", null, null);
            }
        });
    }
    //method is used to check if your App has the SMS sending and receiving permission
    //If not, a dialog will pop up requesting the user for the permission when the App is first run.
    private void checkPermission() {
        int permissionSendSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permissionRecvSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);
        if (permissionSendSMS != PackageManager.PERMISSION_GRANTED &&
                permissionRecvSMS != PackageManager.PERMISSION_GRANTED) {
            String[] permissionNeeded = new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permissionNeeded, 1);
        }
    }

}
