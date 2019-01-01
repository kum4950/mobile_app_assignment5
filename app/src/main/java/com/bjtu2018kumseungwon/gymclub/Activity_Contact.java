package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Contact extends AppCompatActivity {
Button email,sms,call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        email = findViewById(R.id.email);
        sms=findViewById(R.id.sms);
        call=findViewById(R.id.call);
        Intent intent = getIntent();
        final String email_address = intent.getStringExtra("email_address");
        final String phone_number = intent.getStringExtra("phone_number");
        final String tel = "tel:"+phone_number;

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Contact.this,Activity_Email.class);
                intent.putExtra("email_address",email_address);
                startActivity(intent);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"+phone_number));
                startActivity(intent);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(tel));
                startActivity(intent);
            }
        });
    }
}
