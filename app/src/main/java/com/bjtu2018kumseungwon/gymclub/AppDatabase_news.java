package com.bjtu2018kumseungwon.gymclub;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {newsitem.class}, version = 1,exportSchema = false)
public abstract class AppDatabase_news extends RoomDatabase {
    public abstract newsitemDao newsitemDao();
}