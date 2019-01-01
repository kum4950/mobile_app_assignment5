package com.bjtu2018kumseungwon.gymclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasking);
        Button update = findViewById(R.id.update);
        Button add = findViewById(R.id.add);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateintent = new Intent(TaskingActivity.this, UpdateTaskActivity.class);
                startActivity(updateintent);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addintent = new Intent(TaskingActivity.this, AddTaskActivity.class);
                startActivity(addintent);
            }
        });
    }
}
