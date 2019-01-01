package com.bjtu2018kumseungwon.gymclub;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.google.android.gms.tasks.Task;

import java.util.List;

@Dao
public interface newsitemDao {

    @Query("SELECT * FROM newsitem")
    List<newsitem> getAll();

    @Insert
    void insert(newsitem newsitem);
/*
    @Delete
    void delete(Task task);

    @Update
    void update(Task task);
*/
}