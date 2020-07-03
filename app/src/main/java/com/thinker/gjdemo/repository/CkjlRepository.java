package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.CKJLDao;
import com.thinker.gjdemo.database.CkDatabase;
import com.thinker.gjdemo.database.CkjlDatabase;
import com.thinker.gjdemo.database.RkjlDatabase;
import com.thinker.gjdemo.entity.CKJL;

import java.util.List;

public class CkjlRepository {
    
    private LiveData<List<CKJL>> allCksLive;
    private CKJLDao ckjlDao;
    public CkjlRepository(Context context) {
        CkjlDatabase ckjlDatabase = CkjlDatabase.getDatabase(context.getApplicationContext());
        ckjlDao = ckjlDatabase.getCkjlDao();
        allCksLive = ckjlDao.getAll();
    }

    public void insert(CKJL...ckjls){
        new InsertAsyncTask(ckjlDao).execute(ckjls);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(ckjlDao).execute();
    }
    public void delete(CKJL...ckjls){
        new DeleteAsyncTask(ckjlDao).execute(ckjls);
    }
    public LiveData<List<CKJL>> getAll() {
        return allCksLive;
    }
    public List<CKJL> getAllRksList() {
        return ckjlDao.getAllList();
    }

    public LiveData<List<CKJL>> find(String pattern) {
        return ckjlDao.find("%" + pattern + "%");
    }

    //匿名内部类，插入数据
    static class InsertAsyncTask extends AsyncTask<CKJL,Void,Void> {

        private CKJLDao ckjlDao;

        public InsertAsyncTask(CKJLDao ckjlDao) {
            this.ckjlDao = ckjlDao;
        }

        @Override
        protected Void doInBackground(CKJL...ckjls) {
            ckjlDao.insert(ckjls);
            return null;
        }
    }

    //删除数据
    static class DeleteAsyncTask extends AsyncTask<CKJL,Void,Void>{
        private CKJLDao ckjlDao;

        public DeleteAsyncTask(CKJLDao ckjlDao) {
            this.ckjlDao = ckjlDao;
        }

        @Override
        protected Void doInBackground(CKJL...ckjls) {
            ckjlDao.delete(ckjls);
            return null;
        }
    }

    //清空数据
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private CKJLDao ckjlDao;

        public DeleteAllAsyncTask(CKJLDao ckjlDao) {
            this.ckjlDao = ckjlDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            ckjlDao.deleteAll();
            return null;
        }
    }
}
