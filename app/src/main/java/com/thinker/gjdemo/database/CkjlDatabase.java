package com.thinker.gjdemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thinker.gjdemo.dao.CKJLDao;
import com.thinker.gjdemo.dao.RKJLDao;
import com.thinker.gjdemo.entity.CKJL;
import com.thinker.gjdemo.entity.RKJL;

//singleton 单例模式
//databse 实例化是耗资源的操作，不能让database出现多个实例，即使程序中呼叫多个database实例，程序也返回的是同一个实例
@Database(entities = {CKJL.class},version = 1,exportSchema = false)
public abstract class CkjlDatabase extends RoomDatabase{
    private static CkjlDatabase INSTANCE;
    //单例模式代码
    public static synchronized CkjlDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CkjlDatabase.class,"ckjl_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract CKJLDao getCkjlDao();

}
