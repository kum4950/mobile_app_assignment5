package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        final EditText email_address = findViewById(R.id.email_address);
        final EditText email_subject = findViewById(R.id.email_subject);
        final EditText email_text = findViewById(R.id.email_text);
        Button send_button =findViewById(R.id.send_button);
        Intent intent = getIntent();
        String address = intent.getStringExtra("email_address");
        email_address.setText(address);


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_address = email_address.getText().toString();
                String str_subject = email_subject.getText().toString();
                String str_text = email_text.getText().toString();
                String address_List[] = {str_address};
                String[] CC ={""};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL,address_List);
                intent.putExtra(Intent.EXTRA_CC,CC);
                intent.putExtra(Intent.EXTRA_SUBJECT,str_subject);
                intent.putExtra(Intent.EXTRA_TEXT,str_text);
                startActivity(intent);
            }
        });
    }
}
