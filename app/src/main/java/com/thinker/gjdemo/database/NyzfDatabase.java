package com.thinker.gjdemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thinker.gjdemo.dao.NYZFDao;
import com.thinker.gjdemo.entity.NYZF;
//singleton 单例模式
//databse 实例化是耗资源的操作，不能让database出现多个实例，即使程序中呼叫多个database实例，程序也返回的是同一个实例
@Database(entities = {NYZF.class},version = 1,exportSchema = false)
public abstract class NyzfDatabase extends RoomDatabase{
    private static NyzfDatabase INSTANCE;
    //单例模式代码
    public static synchronized NyzfDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NyzfDatabase.class,"nyzf_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract NYZFDao getNyzfDao();

}
