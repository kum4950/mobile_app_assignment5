package com.bjtu2018kumseungwon.gymclub;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    List<Tasks> getAll();

    @Insert
    void insert(Tasks tasks);

    @Delete
    void delete(Tasks tasks);

    @Update
    void update(Tasks tasks);

}