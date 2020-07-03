package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.ZZDDao;
import com.thinker.gjdemo.database.CkjlDatabase;
import com.thinker.gjdemo.database.ZzdDatabase;
import com.thinker.gjdemo.entity.ZZD;

import java.util.List;

public class ZzdRepository {
    
    private LiveData<List<ZZD>> allZzdLive;
    private ZZDDao zzdDao;
    public ZzdRepository(Context context) {
        ZzdDatabase zzdDatabase = ZzdDatabase.getDatabase(context.getApplicationContext());
        zzdDao = zzdDatabase.getZZDao();
        allZzdLive = zzdDao.getAll();
    }

    public void insert(ZZD...zzds){
        new InsertAsyncTask(zzdDao).execute(zzds);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(zzdDao).execute();
    }
    public void delete(ZZD...zzds){
        new DeleteAsyncTask(zzdDao).execute(zzds);
    }
    public LiveData<List<ZZD>> getAll() {
        return allZzdLive;
    }
    public List<ZZD> getAllRksList() {
        return zzdDao.getAllList();
    }

    public LiveData<List<ZZD>> find(String pattern) {
        return zzdDao.find("%" + pattern + "%");
    }

    //匿名内部类，插入数据
    static class InsertAsyncTask extends AsyncTask<ZZD,Void,Void> {

        private ZZDDao zzdDao;

        public InsertAsyncTask(ZZDDao zzdDao) {
            this.zzdDao = zzdDao;
        }

        @Override
        protected Void doInBackground(ZZD...zzds) {
            zzdDao.insert(zzds);
            return null;
        }
    }

    //删除数据
    static class DeleteAsyncTask extends AsyncTask<ZZD,Void,Void>{
        private ZZDDao zzdDao;

        public DeleteAsyncTask(ZZDDao zzdDao) {
            this.zzdDao = zzdDao;
        }

        @Override
        protected Void doInBackground(ZZD...zzds) {
            zzdDao.delete(zzds);
            return null;
        }
    }

    //清空数据
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private ZZDDao zzdDao;

        public DeleteAllAsyncTask(ZZDDao zzdDao) {
            this.zzdDao = zzdDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            zzdDao.deleteAll();
            return null;
        }
    }
}
