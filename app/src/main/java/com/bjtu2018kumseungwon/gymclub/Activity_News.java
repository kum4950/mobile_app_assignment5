package com.bjtu2018kumseungwon.gymclub;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_News extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<newsitem> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getTasks();
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<newsitem>> {

            @Override
            protected List<newsitem> doInBackground(Void... voids) {
                int internet = Get_Internet(Activity_News.this);
                if (internet == 0) {
                    newsList = DatabaseClient_news
                            .getInstance(getApplicationContext())
                            .getAppDatabase()
                            .newsitemDao()
                            .getAll();
                    return newsList;
                } else {
                    URL url = null;
                    String serverUrl = "http://10.0.2.2:8080/Gymdoc/";
                    for (int a = 1; a < 11; a++) {
                        try {
                            //url=new URL("http://10.0.2.2:8080/Gymtrainer/trainer1.jpg");
                            url = new URL(serverUrl + a + ".txt");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.connect();
                            int i;
                            InputStream is = conn.getInputStream();

                            StringBuffer buffer = new StringBuffer();
                            byte[] b = new byte[4096];
                            while ((i = is.read(b)) != -1) {
                                buffer.append(new String(b, 0, i));
                            }
                            String str = buffer.toString();
                            System.out.println(str);

                            newsitem newsitem = new newsitem();
                            newsitem.setContext(str);
                            newsList.add(newsitem);
                            DatabaseClient_news.getInstance(getApplicationContext()).getAppDatabase()
                                    .newsitemDao()
                                    .insert(newsitem);
                            is.close();
                            conn.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                    return newsList;
                }
            }

            @Override
            protected void onPostExecute(List<newsitem> newsList) {
                super.onPostExecute(newsList);
                newsitemAdapter adapter = new newsitemAdapter(Activity_News.this, newsList);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    /*
    Get_Internet
    : 인터넷 연결환경에 대해 체크한다.
    0을 리턴할 경우, 인터넷 연결끊김
    1을 리턴할 경우, 와이파이 연결상태
    2를 연결할 경우, 인터넷 연결상태
     */
    public static int Get_Internet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return 2;
            }
        }
        return 0;
    }

}