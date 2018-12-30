package com.bjtu2018kumseungwon.gymclub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class Activity_Video extends AppCompatActivity {
    ProgressDialog mDialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //get intent extra
        Intent intent = getIntent();
        final String videoURL = intent.getStringExtra("videoURL");

        videoView = findViewById(R.id.videoView);
        btnPlayPause = findViewById(R.id.btn_play_pause);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new ProgressDialog(Activity_Video.this);
                mDialog.setMessage("please wait...");
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
                try{
                    if(!videoView.isPlaying()) {
                        Uri uri = Uri.parse(videoURL);
                        videoView.setVideoURI(uri);
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                btnPlayPause.setImageResource(R.drawable.ic_pause);
                            }
                        });
                    }else
                    {
                        videoView.pause();
                        btnPlayPause.setImageResource(R.drawable.ic_play);
                        mDialog.dismiss();
                        return;
                    }
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                videoView.requestFocus(); //using dialog , show buffering
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { //completed ready for playing video
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mDialog.dismiss();
                        mp.setLooping(true);
                        videoView.start();
                        btnPlayPause.setImageResource(R.drawable.ic_pause);
                    }
                });
            }
        });

    }
}
