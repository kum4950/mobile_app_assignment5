package com.bjtu2018kumseungwon.gymclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Activity_Chat extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    FrameLayout frame;
    int imageIndex = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        frame=findViewById(R.id.frame);
        frame.setClickable(true);

        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });
    }

    private void changeImage(){
        if(imageIndex==1){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex = 0;
        }else if (imageIndex==0){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex = 1;
        }
    }
}
