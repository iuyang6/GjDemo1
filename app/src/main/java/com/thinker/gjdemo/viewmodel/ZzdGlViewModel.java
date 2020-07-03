package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.entity.ZZD;
import com.thinker.gjdemo.repository.ZzdRepository;

import java.util.List;

public class ZzdGlViewModel extends AndroidViewModel {
    private ZzdRepository zzdRepository;

    public ZzdGlViewModel(@NonNull Application application) {
        super(application);
        zzdRepository = new ZzdRepository(application);
    }

    public LiveData<List<ZZD>> getAll() {
        return zzdRepository.getAll();
    }

    public List<ZZD> getAllRkList () {
        return zzdRepository.getAllRksList();
    }
    public LiveData<List<ZZD>> find(String pattern) {
        return zzdRepository.find(pattern);
    }
    public void insert(ZZD... zzds){
        zzdRepository.insert(zzds);
    }
    public void deleteAll(ZZD... zzds){
        zzdRepository.deleteAll();
    }
    public void delete(ZZD... zzds){
        zzdRepository.delete(zzds);
    }
}