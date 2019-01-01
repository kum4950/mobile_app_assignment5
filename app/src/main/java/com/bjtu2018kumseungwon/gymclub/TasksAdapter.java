package com.bjtu2018kumseungwon.gymclub;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<item> itemList;

    public TasksAdapter(Context mCtx, List<item> itemList) {
        this.mCtx = mCtx;
        this.itemList = itemList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        item item = itemList.get(position);
        holder.textViewTask.setText(item.getTrainer_name());
        holder.textViewDesc.setText(item.getEmail_address());
        holder.textViewFinishBy.setText(item.getPhone_number());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTask, textViewDesc, textViewFinishBy;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);

        }

    }
}