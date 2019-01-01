package com.bjtu2018kumseungwon.gymclub;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient_news {

    private Context mCtx;
    private static DatabaseClient_news mInstance;

    //our app database object
    private AppDatabase_news appDatabase_news;

    private DatabaseClient_news(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase_news = Room.databaseBuilder(mCtx, AppDatabase_news.class, "news").build();
    }

    public static synchronized DatabaseClient_news getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient_news(mCtx);
        }
        return mInstance;
    }

    public AppDatabase_news getAppDatabase() {
        return appDatabase_news;
    }
}