package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.RKJL;
import com.thinker.gjdemo.repository.CkRepository;
import com.thinker.gjdemo.repository.RkjlRepository;

import java.util.List;

public class RuKuViewModel extends AndroidViewModel {
    private RkjlRepository rkjlRepository;

    public RuKuViewModel(@NonNull Application application) {
        super(application);
        rkjlRepository = new RkjlRepository(application);
    }

    public LiveData<List<RKJL>> getAll() {
        return rkjlRepository.getAll();
    }

    public List<RKJL> getAllRkList () {
        return rkjlRepository.getAllRksList();
    }
    public LiveData<List<RKJL>> find(String pattern) {
        return rkjlRepository.find(pattern);
    }
    public void insert(RKJL... rkjls){
        rkjlRepository.insert(rkjls);
    }
    public void deleteAll(RKJL... rkjls){
        rkjlRepository.deleteAll();
    }
    public void delete(RKJL... rkjls){
        rkjlRepository.delete(rkjls);
    }
}