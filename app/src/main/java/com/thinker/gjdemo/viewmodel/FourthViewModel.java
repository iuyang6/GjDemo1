package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.entity.NYZF;
import com.thinker.gjdemo.repository.NyzfRepository;

import java.util.List;

public class FourthViewModel extends AndroidViewModel {
    private NyzfRepository nyzfRepository;

    public FourthViewModel(@NonNull Application application) {
        super(application);
        nyzfRepository = new NyzfRepository(application);
    }

    public LiveData<List<NYZF>> getAllNyzfsLive() {
        return nyzfRepository.getAllNyzfsLive();
    }

    public void insert(NYZF... nyzfs){
        nyzfRepository.insert(nyzfs);
    }
    public void deleteAll(NYZF... nyzfs){
        nyzfRepository.deleteAll();
    }
    public void delete(NYZF... nyzfs){
        nyzfRepository.delete(nyzfs);
    }

}