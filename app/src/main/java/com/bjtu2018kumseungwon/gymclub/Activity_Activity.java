package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<VideoURL> videoList = new ArrayList<>();
    List<item_activity> item_list;
    Adapter_activity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        new BackgroundTask().execute();


    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://10.0.2.2:8080/Gym/views.php";
        } //실행 순서 1

        @Override
        protected String doInBackground(Void... voids) { // 실행 순서 2
            try {
                System.out.println("들어왔니?");
                URL url = new URL(target);//URL 객체 생성
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                System.out.println(stringBuilder.toString().trim());
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                System.out.println("here");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) { // ui를 만드는데 사용된다고함.
            try {
                System.out.println("여기는?");
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String video_name, views;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    video_name = object.getString("video_name");
                    views = object.getString("views");
                    System.out.println(video_name);
                    System.out.println(views);

                    videoList.add(new VideoURL(video_name, Integer.parseInt(views)));
                    count++;
                    System.out.println(count);
                }
                //set up recyclerview with the adapter
                recyclerView = findViewById(R.id.rv_list);
                item_list = new ArrayList<>();
                System.out.println(videoList.size());
                int size = videoList.size();

                System.out.println("2");
                System.out.println(size);
                for (int a = 0; a < size; a++) {
                    item_list.add(
                            new item_activity(
                                    videoList.get(a).getClass_Name(),
                                    videoList.get(a).getViews(),
                                    videoList.get(a).getVideoURL()));
                }
                //item_list.get(0).profilePhoto
                adapter = new Adapter_activity(Activity_Activity.this, item_list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Activity.this));


            } catch (Exception e) {
                System.out.println("여기닷");
                e.printStackTrace();
            }
        }
    }
}


