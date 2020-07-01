package com.thinker.gjdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thinker.gjdemo.entity.CLGL;

import java.util.List;

@Dao  //Database access object
public interface CLGLDao {
    @Insert
    void insertCL(CLGL... clgls);

    @Update
    void updateCL(CLGL... clgls);

    //清除所有的执法信息
    @Query("DELETE FROM CLGL")
    void deleteCls();

    //查询所有的执法信息
    @Query("SELECT * FROM CLGL ORDER BY ID DESC")
    LiveData<List<CLGL>>getAllClsLive();

    //条件查询
    @Query("SELECT * FROM CLGL WHERE clmc LIKE :pattern ORDER BY ID DESC")
    LiveData<List<CLGL>>findclsLive(String pattern);
    //删除单条信息
    @Delete
    void deleteCl(CLGL... clgls);
}
