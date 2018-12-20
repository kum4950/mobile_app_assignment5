package com.bjtu2018kumseungwon.gymclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Activity_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //set up recyclerview with the adapter
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        List<item> mlist =new ArrayList<>();
        mlist.add(new item(R.drawable.fit1,"gymclub1",R.drawable.trainer1,2500));
        mlist.add(new item(R.drawable.fit2,"gymclub2",R.drawable.trainer2,3700));
        mlist.add(new item(R.drawable.fit3,"gymclub3",R.drawable.trainer3,5800));
        mlist.add(new item(R.drawable.fit4,"gymclub4",R.drawable.trainer4,22300));
        mlist.add(new item(R.drawable.fit5,"gymclub5",R.drawable.trainer5,8700));
        mlist.add(new item(R.drawable.fit6,"gymclub6",R.drawable.trainer6,2270));
        mlist.add(new item(R.drawable.fit7,"gymclub7",R.drawable.trainer7,2509));
        mlist.add(new item(R.drawable.fit8,"gymclub8",R.drawable.trainer8,2400));
        mlist.add(new item(R.drawable.fit9,"gymclub9",R.drawable.trainer9,2120));
        mlist.add(new item(R.drawable.fit10,"gymclub10",R.drawable.trainer10,1234));


        Adapter adapter = new Adapter(this, mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
