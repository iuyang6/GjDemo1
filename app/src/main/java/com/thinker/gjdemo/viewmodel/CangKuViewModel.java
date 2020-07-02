package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.repository.CkRepository;
import com.thinker.gjdemo.repository.ClglRepository;

import java.util.List;

public class CangKuViewModel extends AndroidViewModel {
    private CkRepository ckRepository;

    public CangKuViewModel(@NonNull Application application) {
        super(application);
        ckRepository = new CkRepository(application);
    }

    public LiveData<List<CK>> getAll() {
        return ckRepository.getAll();
    }

    public List<CK> getAllList () {
        return ckRepository.getAllCksList();
    }
    public LiveData<List<CK>> find(String pattern) {
        return ckRepository.find(pattern);
    }
    public void insert(CK... cks){
        ckRepository.insert(cks);
    }
    public void deleteAll(CK... cks){
        ckRepository.deleteAll();
    }
    public void delete(CK... cks){
        ckRepository.delete(cks);
    }
}