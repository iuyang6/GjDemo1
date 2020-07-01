package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.CKDao;
import com.thinker.gjdemo.database.CkDatabase;
import com.thinker.gjdemo.entity.CK;

import java.util.List;

public class CkRepository {
    private LiveData<List<CK>> allCksLive;
    private CKDao ckDao;
    public CkRepository(Context context) {
        CkDatabase ckDatabase = CkDatabase.getDatabase(context.getApplicationContext());
        ckDao = ckDatabase.getCKDao();
        allCksLive = ckDao.getAll();
    }

    public void insert(CK...cks){
        new InsertAsyncTask(ckDao).execute(cks);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(ckDao).execute();
    }
    public void delete(CK...cks){
        new DeleteAsyncTask(ckDao).execute(cks);
    }
    public LiveData<List<CK>> getAll() {
        return allCksLive;
    }

    public LiveData<List<CK>> find(String pattern) {
        return ckDao.find("%" + pattern + "%");
    }

    //匿名内部类，插入数据
    static class InsertAsyncTask extends AsyncTask<CK,Void,Void> {
        private CKDao ckDao;

        public InsertAsyncTask(CKDao ckDao) {
            this.ckDao = ckDao;
        }

        @Override
        protected Void doInBackground(CK...cks) {
            ckDao.insert(cks);
            return null;
        }
    }

    //删除数据
    static class DeleteAsyncTask extends AsyncTask<CK,Void,Void>{
        private CKDao ckDao;

        public DeleteAsyncTask(CKDao ckDao) {
            this.ckDao = ckDao;
        }

        @Override
        protected Void doInBackground(CK...cks) {
            ckDao.delete(cks);
            return null;
        }
    }

    //清空数据
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private CKDao ckDao;

        public DeleteAllAsyncTask(CKDao ckDao) {
            this.ckDao = ckDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            ckDao.deleteAll();
            return null;
        }
    }
}
