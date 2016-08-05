package com.example.brendan.sendsms;

import android.support.v7.app.AppCompatActivity;

import android.net.Uri;
import android.content.Intent;
import android.view.Menu;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText txtPhoneNo;
    EditText txtMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhoneNo = (EditText) this.findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) this.findViewById(R.id.txtMessage);
        btnSend    = (Button)this.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Get phone number and message sms
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();

                // If phone number & message are not empty
                if(phoneNo.length()>0 && message.length()>0) {
                    sendMessage(phoneNo, message);

                // If phone number or message not empty
                }else{
                    Toast.makeText(getBaseContext(), "Please enter both user number and message. ", Toast.LENGTH_LONG).show();
                }

            }



        });


    }

    private void sendMessage(String phoneNo, String message){


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent. ", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "SMS Fail. Please try again! ", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }



  /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }*/
}
