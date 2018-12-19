package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_Intro extends AppCompatActivity {

    Handler handle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        handle= new Handler();
        // while 3 second, show intro
        handle.postDelayed(rIntent,3000);

    }

    Runnable rIntent = new Runnable() {
        @Override
        public void run() {
            Intent Main = new Intent(Activity_Intro.this,Activity_Main.class);
            startActivity(Main);
            finish();

            //fade in -> fade out
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    //in intro class , when press Back key , we recall rIntent.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handle.removeCallbacks(rIntent);
    }
}
