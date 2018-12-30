package com.bjtu2018kumseungwon.gymclub;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        // url list
        List<VideoURL> videoList = new ArrayList<>();
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/5 Best Home Workout Tips (APPLY TO ALL HOME WORKOUTS!).mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/6 PACK ABS For Beginners You Can Do Anywhere - 2018.mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/7 Bicep Exercises for Bigger Arms (DON'T SKIP THESE!).mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/7 Exercises For A Flat Stomach At Home - Fitness With Namrata Purohit - Glamrs.mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/10 Exercises To Get WIDER HIPS - Tips To Reduce HIP DIPS - Scientific Approach.mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/12 Exercises To Change Your Life.mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/How to Get Bigger Biceps (5 Mistakes You’re Probably Making).mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/How To Train For Mass - Arnold Schwarzenegger's Blueprint Training Program.mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/No Gym Full Body Workout.mp4"));
        videoList.add(new VideoURL("http://10.0.2.2:8080/Gymvideo/Which Press is Best for Bigger Shoulders (IRON FACE-OFF).mp4"));


        //set up recyclerview with the adapter
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        List<item_activity> mlist = new ArrayList<>();
        mlist.add(new item_activity(videoList.get(0).getClass_Name(), R.drawable.trainer1, 2500, videoList.get(0).getVideoURL()));
        mlist.add(new item_activity(videoList.get(1).getClass_Name(), R.drawable.trainer2, 3700, videoList.get(1).getVideoURL()));
        mlist.add(new item_activity(videoList.get(2).getClass_Name(), R.drawable.trainer3, 5800, videoList.get(2).getVideoURL()));
        mlist.add(new item_activity(videoList.get(3).getClass_Name(), R.drawable.trainer4, 22300, videoList.get(3).getVideoURL()));
        mlist.add(new item_activity(videoList.get(4).getClass_Name(), R.drawable.trainer5, 8700, videoList.get(4).getVideoURL()));
        mlist.add(new item_activity(videoList.get(5).getClass_Name(), R.drawable.trainer6, 2270, videoList.get(5).getVideoURL()));
        mlist.add(new item_activity(videoList.get(6).getClass_Name(), R.drawable.trainer7, 2509, videoList.get(6).getVideoURL()));
        mlist.add(new item_activity(videoList.get(7).getClass_Name(), R.drawable.trainer8, 2400, videoList.get(7).getVideoURL()));
        mlist.add(new item_activity(videoList.get(8).getClass_Name(), R.drawable.trainer9, 2120, videoList.get(8).getVideoURL()));
        mlist.add(new item_activity(videoList.get(9).getClass_Name(), R.drawable.trainer10, 1234, videoList.get(9).getVideoURL()));


        Adapter_activity adapter = new Adapter_activity(this, mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
