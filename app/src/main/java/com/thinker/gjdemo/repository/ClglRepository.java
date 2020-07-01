package com.thinker.gjdemo.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.dao.CLGLDao;
import com.thinker.gjdemo.dao.NYZFDao;
import com.thinker.gjdemo.database.ClglDatabase;
import com.thinker.gjdemo.entity.CLGL;

import java.util.List;

public class ClglRepository {
    private LiveData<List<CLGL>> allClsLive;
    private CLGLDao clglDao;
    public ClglRepository(Context context) {
        ClglDatabase clglDatabase = ClglDatabase.getDatabase(context.getApplicationContext());
        clglDao = clglDatabase.getClglDao();
        allClsLive = clglDao.getAllClsLive();
    }

    public void insert(CLGL... clgls){
        new InsertAsyncTask(clglDao).execute(clgls);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(clglDao).execute();
    }
    public void delete(CLGL... clgls){
        new DeleteAsyncTask(clglDao).execute(clgls);
    }
    public LiveData<List<CLGL>> getAllClsLive() {
        return allClsLive;
    }

    public LiveData<List<CLGL>> findClsLive(String pattern) {
        return clglDao.findclsLive("%" + pattern + "%");
    }

    //匿名内部类，插入数据
    static class InsertAsyncTask extends AsyncTask<CLGL,Void,Void> {
        private CLGLDao clglDao;

        public InsertAsyncTask(CLGLDao clglDao) {
            this.clglDao = clglDao;
        }

        @Override
        protected Void doInBackground(CLGL... clgls) {
            clglDao.insertCL(clgls);
            return null;
        }
    }

    //删除数据
    static class DeleteAsyncTask extends AsyncTask<CLGL,Void,Void>{
        private CLGLDao clglDao;
        private NYZFDao nyzfDao;

        public DeleteAsyncTask(CLGLDao clglDao) {
            this.clglDao = clglDao;
        }

        @Override
        protected Void doInBackground(CLGL... clgls) {
            clglDao.deleteCl(clgls);
            return null;
        }
    }

    //清空数据
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private CLGLDao clglDao;

        public DeleteAllAsyncTask(CLGLDao clglDao) {
            this.clglDao = clglDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            clglDao.deleteCls();
            return null;
        }
    }
}
