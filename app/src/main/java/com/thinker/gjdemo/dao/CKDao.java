package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.CK;

import java.util.List;

@Dao  //Database access object
public interface CKDao {
    @Insert
    void insert(CK...cks);

    @Update
    void update(CK...cks);

    //清除所有
    @Query("DELETE FROM CK")
    void deleteAll();

    //查询所有
    @Query("SELECT * FROM CK ORDER BY ID DESC")
    LiveData<List<CK>>getAll();

    //条件查询
    @Query("SELECT * FROM ck WHERE ckName LIKE :pattern ORDER BY ID DESC")
    LiveData<List<CK>>find(String pattern);
    //删除单条信息
    @Delete
    void delete(CK...cks);
}
