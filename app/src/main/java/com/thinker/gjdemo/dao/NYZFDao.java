package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.NYZF;

import java.util.List;

@Dao  //Database access object
public interface NYZFDao {
    @Insert
    void insertNysfs(NYZF... nyzfs);

    @Update
    void updateNysfs(NYZF... nyzfs);

    //清除所有的执法信息
    @Query("DELETE FROM NYZF")
    void deleteNysfs();

    //查询所有的执法信息
    @Query("SELECT * FROM NYZF ORDER BY ID DESC")
//    List<NYZF> getAllNyzfs();
    LiveData<List<NYZF>>getAllNyzfsLive();
    //删除单条信息
    @Delete
    void deleteNyzf(NYZF...nyzfs);
}
