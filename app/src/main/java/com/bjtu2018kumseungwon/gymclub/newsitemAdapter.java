package com.bjtu2018kumseungwon.gymclub;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

public class newsitemAdapter extends RecyclerView.Adapter<newsitemAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<newsitem> newsitemList;

    public newsitemAdapter(Context mCtx, List<newsitem> newsitemList) {
        this.mCtx = mCtx;
        this.newsitemList = newsitemList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.activity_new_item, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        newsitem t = newsitemList.get(position);
        System.out.println(t.getContext());
        holder.textView.setText(t.getContext());
    }

    @Override
    public int getItemCount() {
        return newsitemList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.context);

        }

    }
}