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

                mData.get(position).setNbViews(mData.get(position).getNbViews()+1);
                holder.tv_nbViews.setText((mData.get(position).getNbViews() + " Views"));

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            //서버로부터 받는 데이터는 JSON타입의 객체이다.
                            JSONObject jsonResponse = new JSONObject(response);
                            //그중 key 값이 "success" 인것을 가져온다.
                            boolean success = jsonResponse.getBoolean("success");

                            //회원 가입 성공시 success 값이 true 임
                            if(success){
                                Toast.makeText(mContext.getApplicationContext(),"success",Toast.LENGTH_SHORT).show();

                                //알림상자를 만들어서 보여줌
                                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                                builder.setMessage("update views success!!")
                                        .setPositiveButton("ok",null)
                                        .create()
                                        .show();

                                //call INTENT// url
                                Intent videoIntent = new Intent(mContext, Activity_Video.class);
                                videoIntent.putExtra("videoURL", URL);
                                mContext.startActivity(videoIntent);
                            }
                            //회원 가입 실패시 success 값이 false 임
                            else{
                                Toast.makeText(mContext.getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
                                //알림상자를 만들어서 보여줌
                                android.support.v7.app.AlertDialog.Builder builder= new android.support.v7.app.AlertDialog.Builder(mContext);
                                builder.setMessage("update views fail!!")
                                        .setNegativeButton("ok",null)
                                        .create()
                                        .show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };//responseLister 끝

                //volley 사용법
                //1.RequestObject 를 생성한다. 이때 서버로부터 데이터를 받을 responseListener를 반드시 넘겨준다.
                ActivityRequest activityRequest = new ActivityRequest(mData.get(position).getClass_Name(), mData.get(position).getNbViews(),responseListener);
                //2.RequestQueue를 생성한다.
                RequestQueue queue = Volley.newRequestQueue(mContext);
                //3.RequestQueue에 RequestObject를 넘겨준다.
                queue.add(activityRequest);

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