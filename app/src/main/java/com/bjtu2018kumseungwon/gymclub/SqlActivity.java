package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class SqlActivity extends AppCompatActivity {

    private FloatingActionButton button;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTasks();

    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<item>> {

            @Override
            protected List<item> doInBackground(Void... voids) {
                List<item> item_list = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .itemDao()
                        .getAll();
                return item_list;
            }

            @Override
            protected void onPostExecute(List<item> item) {
                super.onPostExecute(item);
                TasksAdapter adapter = new TasksAdapter(SqlActivity.this,item);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}