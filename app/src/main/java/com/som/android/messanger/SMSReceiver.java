package com.som.android.messanger;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    /**
     * TAG used for Debug-Logging
     */
    private static final String LOG_TAG = "SMSReceiver";
String number,sms;

    /**
     * The Action fired by the Android-System when a SMS was received.
     * We are using the Default Package-Visibility
     */
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            // if(message starts with SMStretcher recognize BYTE)
            StringBuilder sb = new StringBuilder();

                        /* The SMS-Messages are 'hiding' within the extras of the Intent. */
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                                /* Get all messages contained in the Intent*/
                android.telephony.SmsMessage[] messages =
                        Telephony.Sms.Intents.getMessagesFromIntent(intent);

                                /* Feed the StringBuilder with all Messages found. */
                for (android.telephony.SmsMessage currentMessage : messages) {
                    sb.append("Received compressed SMSnFrom: ");
                                        /* Sender-Number */
                    sb.append(currentMessage.getDisplayOriginatingAddress());
                    number=""+currentMessage.getDisplayOriginatingAddress();
                    sb.append("n----Message----n");
                                        /* Actual Message-Content */
                    sb.append(currentMessage.getDisplayMessageBody());
                    sms=""+currentMessage.getDisplayMessageBody();
                }
            }
                        /* Logger Debug-Output */
            Log.i(LOG_TAG, "[SMSApp] onReceiveIntent: " + sb);






            //the appbar notification ..................



            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.cbook)
                            .setContentTitle(number)
                            .setContentText(sms);
// Creates an explicit intent for an Activity in your app
         // Intent resultIntent = new Intent(this, ResultActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
           TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
           stackBuilder.addParentStack(Messanger.class);
// Adds the Intent that starts the Activity to the top of the stack
            //stackBuilder.addNextIntent(resultIntent);
         //   PendingIntent resultPendingIntent =
         //           stackBuilder.getPendingIntent(
           //                 0,
             //               PendingIntent.FLAG_UPDATE_CURRENT
               //     );
         //   mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build());





















                        /* Show the Notification containing the Message. */
            Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();


               /* Start the Main-Activity */
            Intent i = new Intent(context, Messanger.class);

            context.startActivity(i);

        }
    }

}
