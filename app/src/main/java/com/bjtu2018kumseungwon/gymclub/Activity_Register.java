package com.bjtu2018kumseungwon.gymclub;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class Activity_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nameText = findViewById(R.id.userName);
        final EditText passwordText = findViewById(R.id.userPassword);
        final EditText emailText = findViewById(R.id.userEmail);


        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userEmail = emailText.getText().toString();
                if(!userName.matches("^[a-zA-Z0-9]*$"))
                {
                    Toast.makeText(Activity_Register.this,"\n" +"Please write your name in English only.",Toast.LENGTH_SHORT).show();
                    return;
                }
                //비밀번호 유효성
                if(!userPassword.matches(getString(R.string.pw_validation)))
                {
                    Toast.makeText(Activity_Register.this,"\n" +
                            "Passwords can be from 6 to 16 characters using numbers, and special characters (! @ $% ^ & * Only)" +
                            "\n" +
                            "English is case-sensitive.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!userEmail.matches("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"))
                {
                    Toast.makeText(Activity_Register.this,"\n" +
                            "Please fill in the email format",Toast.LENGTH_SHORT).show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            //서버로부터 받는 데이터는 JSON타입의 객체이다.
                            JSONObject jsonResponse = new JSONObject(response);
                            //그중 key 값이 "success" 인것을 가져온다.
                            boolean success = jsonResponse.getBoolean("success");

                            //회원 가입 성공시 success 값이 true 임
                            if(success){
                                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();

                                //알림상자를 만들어서 보여줌
                                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Register.this);
                                builder.setMessage("register success!!")
                                        .setPositiveButton("ok",null)
                                        .create()
                                        .show();

                                //그리고 첫화면으로 돌아감
                                Intent intent = new Intent(Activity_Register.this, Activity_Login.class);
                                Activity_Register.this.startActivity(intent);
                            }
                            //회원 가입 실패시 success 값이 false 임
                            else{
                                Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
                                //알림상자를 만들어서 보여줌
                                AlertDialog.Builder builder= new AlertDialog.Builder(Activity_Register.this);
                                builder.setMessage("register fail!!")
                                        .setNegativeButton("ok",null)
                                        .create()
                                        .show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };//responseLister 끝

                //volley 사용법
                //1.RequestObject 를 생성한다. 이때 서버로부터 데이터를 받을 responseListener를 반드시 넘겨준다.
                RegisterRequest registerRequest = new RegisterRequest(userName, userPassword, userEmail,responseListener);
                //2.RequestQueue를 생성한다.
                RequestQueue queue = Volley.newRequestQueue(Activity_Register.this);
                //3.RequestQueue에 RequestObject를 넘겨준다.
                queue.add(registerRequest);
            }
        });
    }
}
