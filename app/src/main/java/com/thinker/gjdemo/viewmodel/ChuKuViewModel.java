package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.thinker.gjdemo.entity.CKJL;
import com.thinker.gjdemo.repository.CkjlRepository;

import java.util.List;

public class ChuKuViewModel extends AndroidViewModel {
    private CkjlRepository ckjlRepository;

    public ChuKuViewModel(@NonNull Application application) {
        super(application);
        ckjlRepository = new CkjlRepository(application);
    }

    public LiveData<List<CKJL>> getAll() {
        return ckjlRepository.getAll();
    }

    public List<CKJL> getAllRkList () {
        return ckjlRepository.getAllRksList();
    }
    public LiveData<List<CKJL>> find(String pattern) {
        return ckjlRepository.find(pattern);
    }
    public void insert(CKJL... rkjls){
        ckjlRepository.insert(rkjls);
    }
    public void deleteAll(CKJL... rkjls){
        ckjlRepository.deleteAll();
    }
    public void delete(CKJL... rkjls){
        ckjlRepository.delete(rkjls);
    }
}