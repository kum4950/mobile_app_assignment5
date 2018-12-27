package com.bjtu2018kumseungwon.gymclub;

import android.app.AlertDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize variables
        final EditText usernameText = findViewById(R.id.nameText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);
        final TextView registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Activity_Login.this, Activity_Register.class);
                Activity_Login.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = usernameText.getText().toString();
                final String userPassword = passwordText.getText().toString();
                //user name validation
                if(!userName.matches("^[a-zA-Z0-9]*$"))
                {
                    Toast.makeText(Activity_Login.this,"Please write your name in English and number only.",Toast.LENGTH_SHORT).show();
                    return;
                }
               //password validation
                if(!userPassword.matches(getString(R.string.pw_validation)))
                {
                    Toast.makeText(Activity_Login.this,"\n" +
                            "Passwords can be from 6 to 16 characters using numbers, and special characters (! @ $% ^ & * Only)",Toast.LENGTH_SHORT).show();
                    return;
                }
                //4. 콜백 처리부분(volley 사용을 위한 ResponseListener 구현부분)
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            Toast.makeText(getApplicationContext(), "success" + success, Toast.LENGTH_SHORT).show();

                            // 서버에서 보내준 값이 true 면?
                            if (success) {
                                //Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                                String userName = jsonResponse.getString("userName");
                                String userPassword = jsonResponse.getString("userPassword");
                                // 로그인에 성공했으므로 Activity_Output로 넘어감
                                Intent intent = new Intent(Activity_Login.this, Activity_Output.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userPassword", userPassword);
                                Activity_Login.this.startActivity(intent);
                            } else {
                                // 로그인 실패시
                                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Login.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userName, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Activity_Login.this);
                queue.add(loginRequest);
            }
        });
    }
}
