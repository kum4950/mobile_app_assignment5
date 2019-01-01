package com.bjtu2018kumseungwon.gymclub;

import android.app.AlertDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class Activity_Login extends AppCompatActivity {
    private LoginButton btn_facebook_login;
    private LoginCallback mLoginCallback;
    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize variables
        final EditText usernameText = findViewById(R.id.nameText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);
        final TextView registerButton = findViewById(R.id.registerButton);
        FacebookSdk.sdkInitialize(this.getApplicationContext());


        mCallbackManager = CallbackManager.Factory.create();

        LoginButton fbloginButton = findViewById(R.id.btn_facebook_login);
        fbloginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        fbloginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("result", object.toString());
                                try {
                                    String id=object.getString("id");
                                    String name=object.getString("name");
                                    //do something with the data here
                                    // 로그인에 성공했으므로 Activity_Output로 넘어감
                                    Intent intent = new Intent(Activity_Login.this, Activity_Output.class);
                                    intent.putExtra("userName",name);
                                    intent.putExtra("userPassword", id);
                                    Activity_Login.this.startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace(); //something's seriously wrong here
                                }
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();

                        }
                    @Override
                    public void onCancel() {

                    }
                    @Override
                    public void onError(FacebookException error) {
                        Log.e("LoginErr",error.toString());
                    }
                });


        //
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

    //facebook
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

    }
}
