package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.repository.ClglRepository;

import java.util.List;

public class ClglViewModel extends AndroidViewModel {
    private ClglRepository clglRepository;

    public ClglViewModel(@NonNull Application application) {
        super(application);
        clglRepository = new ClglRepository(application);
    }

    public LiveData<List<CLGL>> getAllClsLive() {
        return clglRepository.getAllClsLive();
    }

    public List<CLGL> getAllClsList() {
        return clglRepository.getAllList();
    }
    public LiveData<List<CLGL>> findClsLive(String pattern) {
        return clglRepository.findClsLive(pattern);
    }
    public void insert(CLGL... clgls){
        clglRepository.insert(clgls);
    }
    public void deleteAll(CLGL... clgls){
        clglRepository.deleteAll();
    }
    public void delete(CLGL... clgls){
        clglRepository.delete(clgls);
    }
}