package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.RKJL;

import java.util.List;

@Dao  //Database access object
public interface RKJLDao {
    @Insert
    void insert(RKJL...rkjls);

    @Update
    void update(RKJL...rkjls);

    //清除所有
    @Query("DELETE FROM RKJL")
    void deleteAll();

    //查询所有
    @Query("SELECT * FROM RKJL ORDER BY ID DESC")
    LiveData<List<RKJL>>getAll();

    //查询所有
    @Query("SELECT * FROM RKJL ORDER BY ID DESC")
    List<RKJL>getAllList();

    //条件查询
    @Query("SELECT * FROM RKJL WHERE ckName LIKE :pattern ORDER BY ID DESC")
    LiveData<List<RKJL>>find(String pattern);
    //删除单条信息
    @Delete
    void delete(RKJL...rkjls);
}
