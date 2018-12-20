package com.bjtu2018kumseungwon.gymclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Activity_Looking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking);
        //set the statue bar backgroud to transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //set up recyclerview with the adapter
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        List<item> mlist =new ArrayList<>();
        mlist.add(new item(R.drawable.trainer1,"Trainer1",R.drawable.trainer1,2500));
        mlist.add(new item(R.drawable.trainer2,"Trainer2",R.drawable.trainer2,3700));
        mlist.add(new item(R.drawable.trainer3,"Trainer3",R.drawable.trainer3,5800));
        mlist.add(new item(R.drawable.trainer4,"Trainer4",R.drawable.trainer4,22300));
        mlist.add(new item(R.drawable.trainer5,"Trainer5",R.drawable.trainer5,8700));
        mlist.add(new item(R.drawable.trainer6,"Trainer6",R.drawable.trainer6,2270));
        mlist.add(new item(R.drawable.trainer7,"Trainer7",R.drawable.trainer7,2509));
        mlist.add(new item(R.drawable.trainer8,"Trainer8",R.drawable.trainer8,2400));
        mlist.add(new item(R.drawable.trainer9,"Trainer9",R.drawable.trainer9,2120));
        mlist.add(new item(R.drawable.trainer10,"Trainer10",R.drawable.trainer10,1234));


        Adapter adapter = new Adapter(this, mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
