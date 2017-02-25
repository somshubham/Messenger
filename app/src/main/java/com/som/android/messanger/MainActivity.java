package com.som.android.messanger;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText number;
    EditText msg;
    String numberValue;
    String msgValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b=(Button)findViewById(R.id.button2);
         number=(EditText)findViewById(R.id.editText2);
         msg=(EditText)findViewById(R.id.editText4);
         b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberValue=number.getText().toString();
                msgValue=msg.getText().toString();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(numberValue, null, msgValue, null,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",Toast.LENGTH_LONG).show();



                finish();





            }
        });






    }
}
