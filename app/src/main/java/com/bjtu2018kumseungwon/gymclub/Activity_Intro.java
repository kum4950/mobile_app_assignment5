package com.bjtu2018kumseungwon.gymclub;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;


public class Activity_Intro extends AppCompatActivity {
    private Context mContext;

    Handler handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mContext = getApplicationContext();
        //getHashKey(mContext);

        handle = new Handler();
        // while 3 second, show intro
        handle.postDelayed(rIntent, 3000);
    }

    public static String getHashKey(Context context) {
        final String TAG = "KeyHash";
        String keyHash = null;
        try {
            PackageInfo info =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
                Log.d(TAG, keyHash);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
        if (keyHash != null) {
            return keyHash;
        } else {
            return null;
        }
    }

    Runnable rIntent = new Runnable() {
        @Override
        public void run() {
            Intent Main = new Intent(Activity_Intro.this, Activity_Main.class);
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
