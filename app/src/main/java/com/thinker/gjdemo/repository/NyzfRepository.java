package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.NYZFDao;
import com.thinker.gjdemo.database.NyzfDatabase;
import com.thinker.gjdemo.entity.NYZF;

import java.util.List;

public class NyzfRepository {
    private LiveData<List<NYZF>> allNyzfsLive;
    private NYZFDao nyzfDao;
    public NyzfRepository(Context context) {
        NyzfDatabase nyzfDatabase = NyzfDatabase.getDatabase(context.getApplicationContext());
        nyzfDao = nyzfDatabase.getNyzfDao();
        allNyzfsLive = nyzfDao.getAllNyzfsLive();
    }

    public void insert(NYZF... nyzfs){
        new InsertAsyncTask(nyzfDao).execute(nyzfs);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(nyzfDao).execute();
    }
    public void delete(NYZF... nyzfs){
        new DeleteAsyncTask(nyzfDao).execute(nyzfs);
    }
    public LiveData<List<NYZF>> getAllNyzfsLive() {
        return allNyzfsLive;
    }

    static class InsertAsyncTask extends AsyncTask<NYZF,Void,Void> {
        private NYZFDao nyzfDao;

        public InsertAsyncTask(NYZFDao nyzfDao) {
            this.nyzfDao = nyzfDao;
        }

        @Override
        protected Void doInBackground(NYZF... nyzfs) {
            nyzfDao.insertNysfs(nyzfs);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<NYZF,Void,Void>{
        private NYZFDao nyzfDao;

        public DeleteAsyncTask(NYZFDao nyzfDao) {
            this.nyzfDao = nyzfDao;
        }

        @Override
        protected Void doInBackground(NYZF... nyzfs) {
            nyzfDao.deleteNyzf(nyzfs);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private NYZFDao nyzfDao;

        public DeleteAllAsyncTask(NYZFDao nyzfDao) {
            this.nyzfDao = nyzfDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            nyzfDao.deleteNysfs();
            return null;
        }
    }
}
