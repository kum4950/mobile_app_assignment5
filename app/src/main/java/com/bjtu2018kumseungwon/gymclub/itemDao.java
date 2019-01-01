package com.bjtu2018kumseungwon.gymclub;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface itemDao {

    @Query("SELECT * FROM item")
    List<item> getAll();

    @Insert
    void insert(item item);
/*
    @Delete
    void delete(item item);

    @Update
    void update(item item);
*/
}