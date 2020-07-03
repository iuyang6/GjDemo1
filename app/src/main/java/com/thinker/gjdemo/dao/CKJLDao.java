package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.CKJL;

import java.util.List;

@Dao  //Database access object
public interface CKJLDao {
    @Insert
    void insert(CKJL... ckjls);

    @Update
    void update(CKJL... ckjls);

    //清除所有
    @Query("DELETE FROM CKJL")
    void deleteAll();

    //查询所有
    @Query("SELECT * FROM CKJL ORDER BY ID DESC")
    LiveData<List<CKJL>>getAll();

    //查询所有
    @Query("SELECT * FROM CKJL ORDER BY ID DESC")
    List<CKJL>getAllList();

    //条件查询
    @Query("SELECT * FROM CKJL WHERE ckName LIKE :pattern ORDER BY ID DESC")
    LiveData<List<CKJL>>find(String pattern);
    //删除单条信息
    @Delete
    void delete(CKJL... ckjls);
}
