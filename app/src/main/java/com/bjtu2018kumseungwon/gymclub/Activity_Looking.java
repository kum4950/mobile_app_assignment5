package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_Looking extends AppCompatActivity {
RecyclerView recyclerView;
List<item> item_list = new ArrayList<>();
Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking);
        //set the statue bar backgroud to transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        new Activity_Looking.BackgroundTask().execute();
        FloatingActionButton information = findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Looking.this,SqlActivity.class);
                startActivity(intent);
            }
        });

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://10.0.2.2:8080/Gym/trainer_list.php";
        } //실행 순서 1

        @Override
        protected String doInBackground(Void... voids) { // 실행 순서 2
            try {
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
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String trainer_name, email_address, phone_number;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    trainer_name = object.getString("trainer_name");
                    email_address = object.getString("email_address");
                    phone_number = object.getString("phone_number");
                    System.out.println(trainer_name);
                    System.out.println(email_address);
                    System.out.println(phone_number);

                    item_list.add(new item(trainer_name,email_address,phone_number));
                    count++;
                    System.out.println(count);
                }
                //set up recyclerview with the adapter
                recyclerView = findViewById(R.id.rv_list);
                adapter = new Adapter(Activity_Looking.this, item_list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Looking.this));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}