package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.CKDao;
import com.thinker.gjdemo.dao.RKJLDao;
import com.thinker.gjdemo.database.CkDatabase;
import com.thinker.gjdemo.database.RkjlDatabase;
import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.RKJL;

import java.util.List;

public class RkjlRepository {
    private LiveData<List<RKJL>> allRksLive;
    private RKJLDao rkjlDao;
    public RkjlRepository(Context context) {
        RkjlDatabase rkjlDatabase = RkjlDatabase.getDatabase(context.getApplicationContext());
        rkjlDao = rkjlDatabase.getRkjlDao();
        allRksLive = rkjlDao.getAll();
    }

    public void insert(RKJL...rkjls){
        new InsertAsyncTask(rkjlDao).execute(rkjls);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(rkjlDao).execute();
    }
    public void delete(RKJL...rkjls){
        new DeleteAsyncTask(rkjlDao).execute(rkjls);
    }
    public LiveData<List<RKJL>> getAll() {
        return allRksLive;
    }
    public List<RKJL> getAllRksList() {
        return rkjlDao.getAllList();
    }

    public LiveData<List<RKJL>> find(String pattern) {
        return rkjlDao.find("%" + pattern + "%");
    }

    //匿名内部类，插入数据
    static class InsertAsyncTask extends AsyncTask<RKJL,Void,Void> {

        private RKJLDao rkjlDao;

        public InsertAsyncTask(RKJLDao rkjlDao) {
            this.rkjlDao = rkjlDao;
        }

        @Override
        protected Void doInBackground(RKJL...rkjls) {
            rkjlDao.insert(rkjls);
            return null;
        }
    }

    //删除数据
    static class DeleteAsyncTask extends AsyncTask<RKJL,Void,Void>{
        private RKJLDao rkjlDao;

        public DeleteAsyncTask(RKJLDao rkjlDao) {
            this.rkjlDao = rkjlDao;
        }

        @Override
        protected Void doInBackground(RKJL...rkjls) {
            rkjlDao.delete(rkjls);
            return null;
        }
    }

    //清空数据
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private RKJLDao rkjlDao;

        public DeleteAllAsyncTask(RKJLDao rkjlDao) {
            this.rkjlDao = rkjlDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            rkjlDao.deleteAll();
            return null;
        }
    }
}
