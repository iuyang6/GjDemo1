package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.SFJLDao;
import com.thinker.gjdemo.database.SfjlDatabase;
import com.thinker.gjdemo.database.ZzdDatabase;
import com.thinker.gjdemo.entity.SFGL;

import java.util.List;

public class SfjlRepository {
    
    private LiveData<List<SFGL>> allSfjlLive;
    private SFJLDao sfjlDao;
    public SfjlRepository(Context context) {
        SfjlDatabase sfjlDatabase = SfjlDatabase.getDatabase(context.getApplicationContext());
        sfjlDao = sfjlDatabase.getSfjlDao();
        allSfjlLive = sfjlDao.getAll();
    }

    public void insert(SFGL...sfjls){
        new InsertAsyncTask(sfjlDao).execute(sfjls);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(sfjlDao).execute();
    }
    public void delete(SFGL...sfjls){
        new DeleteAsyncTask(sfjlDao).execute(sfjls);
    }
    public LiveData<List<SFGL>> getAll() {
        return allSfjlLive;
    }
    public List<SFGL> getAllRksList() {
        return sfjlDao.getAllList();
    }

    public LiveData<List<SFGL>> find(String pattern) {
        return sfjlDao.find("%" + pattern + "%");
    }

    //匿名内部类，插入数据
    static class InsertAsyncTask extends AsyncTask<SFGL,Void,Void> {

        private SFJLDao sfjlDao;

        public InsertAsyncTask(SFJLDao sfjlDao) {
            this.sfjlDao = sfjlDao;
        }

        @Override
        protected Void doInBackground(SFGL...sfjls) {
            sfjlDao.insert(sfjls);
            return null;
        }
    }

    //删除数据
    static class DeleteAsyncTask extends AsyncTask<SFGL,Void,Void>{
        private SFJLDao sfjlDao;

        public DeleteAsyncTask(SFJLDao sfjlDao) {
            this.sfjlDao = sfjlDao;
        }

        @Override
        protected Void doInBackground(SFGL...sfjls) {
            sfjlDao.delete(sfjls);
            return null;
        }
    }

    //清空数据
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private SFJLDao sfjlDao;

        public DeleteAllAsyncTask(SFJLDao sfjlDao) {
            this.sfjlDao = sfjlDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            sfjlDao.deleteAll();
            return null;
        }
    }
}
