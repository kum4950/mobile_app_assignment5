package com.bjtu2018kumseungwon.gymclub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class Adapter_activity extends RecyclerView.Adapter<Adapter_activity.myViewHolder> {
    Context mContext;
    List<item_activity> mData;


    public Adapter_activity(Context mContext, List<item_activity> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.activity_card_item_activity, viewGroup, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        // get video url
        final String URL = mData.get(position).getVideoURL();
        // get video thumbnail
        holder.backgroud_img.setVideoPath(URL);
        holder.backgroud_img.seekTo(6000);

        holder.profile_photo.setImageResource(mData.get(position).getProfilePhoto());
        holder.tv_title.setText(mData.get(position).getClass_Name());
        holder.tv_nbViews.setText((mData.get(position).getNbViews() + " Views"));

        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // INTENT// url
                Intent videoIntent = new Intent(mContext, Activity_Video.class);
                videoIntent.putExtra("videoURL", URL);
                mContext.startActivity(videoIntent);
                mData.get(position).setNbViews(mData.get(position).getNbViews()+1);
                holder.tv_nbViews.setText((mData.get(position).getNbViews() + " Views"));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_photo;
        TextView tv_title, tv_nbViews;
        Button btn_play;
        VideoView backgroud_img;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_photo = itemView.findViewById(R.id.profile_img);
            backgroud_img = itemView.findViewById(R.id.card_background);
            tv_title = itemView.findViewById(R.id.class_name);
            tv_nbViews = itemView.findViewById(R.id.card_nb_click);
            btn_play = itemView.findViewById(R.id.btn_play);
        }
    }
}