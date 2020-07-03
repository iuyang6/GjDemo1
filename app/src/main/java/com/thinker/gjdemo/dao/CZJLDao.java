package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.SFGL;

import java.util.List;

@Dao  //Database access object
public interface CZJLDao {
    @Insert
    void insert(SFGL... sfgls);

    @Update
    void update(SFGL... sfgls);

    //清除所有
    @Query("DELETE FROM SFGL")
    void deleteAll();

    //查询所有
    @Query("SELECT * FROM SFGL ORDER BY ID DESC")
    LiveData<List<SFGL>>getAll();

    //查询所有
    @Query("SELECT * FROM SFGL ORDER BY ID DESC")
    List<SFGL>getAllList();

    //条件查询
    @Query("SELECT * FROM SFGL WHERE zzdName LIKE :pattern ORDER BY ID DESC")
    LiveData<List<SFGL>>find(String pattern);
    //删除单条信息
    @Delete
    void delete(SFGL... sfgls);
}
