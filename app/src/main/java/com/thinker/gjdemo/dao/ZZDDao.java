package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.ZZD;

import java.util.List;

@Dao  //Database access object
public interface ZZDDao {
    @Insert
    void insert(ZZD... zzds);

    @Update
    void update(ZZD... zzds);

    //清除所有
    @Query("DELETE FROM ZZD")
    void deleteAll();

    //查询所有
    @Query("SELECT * FROM ZZD ORDER BY ID DESC")
    LiveData<List<ZZD>>getAll();

    //查询所有
    @Query("SELECT * FROM ZZD ORDER BY ID DESC")
    List<ZZD>getAllList();

    //条件查询
    @Query("SELECT * FROM ZZD WHERE zzdName LIKE :pattern ORDER BY ID DESC")
    LiveData<List<ZZD>>find(String pattern);
    //删除单条信息
    @Delete
    void delete(ZZD... zzds);
}
