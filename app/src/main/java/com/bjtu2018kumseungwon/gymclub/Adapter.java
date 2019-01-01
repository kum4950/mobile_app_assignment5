package com.bjtu2018kumseungwon.gymclub;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    Context mContext;
    List<item> mData;
    Bitmap bitmap;
    Handler handler;
    String serverUrl = "http://10.0.2.2:8080/Gymtrainer/trainer";

    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.activity_card_item, viewGroup, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {

        Runnable runnable = new Adapter_thread(position);
        Thread thread = new Thread(runnable);
        thread.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                holder.backgroud_img.setImageBitmap(bitmap);
                holder.profile_photo.setImageBitmap(bitmap);
            }
        };

        holder.tv_title.setText(mData.get(position).getTrainer_name());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_address = mData.get(position).getEmail_address();
                String phone_number = mData.get(position).getPhone_number();
                Intent contactIntent = new Intent(mContext, Activity_Contact.class);
                contactIntent.putExtra("email_address",email_address);
                contactIntent.putExtra("phone_number",phone_number);
                mContext.startActivity(contactIntent);
            }
        });
    }

    public class Adapter_thread implements Runnable {
        private int position;
        public Adapter_thread(int position){
            this.position = position+1;
        }

        @Override
        public void run() {
            System.out.println(position+"번 쓰레드 동작중");
            URL url = null;
            try{
                //url=new URL("http://10.0.2.2:8080/Gymtrainer/trainer1.jpg");
                url = new URL(serverUrl+position+".jpg");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                handler.sendEmptyMessage(0);
                is.close();
                conn.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_photo, backgroud_img;
        TextView tv_title;
        Button button;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_photo = itemView.findViewById(R.id.profile_img);
            backgroud_img = itemView.findViewById(R.id.card_background);
            tv_title = itemView.findViewById(R.id.card_title);
            button = itemView.findViewById(R.id.btn_question);
        }
    }
}
