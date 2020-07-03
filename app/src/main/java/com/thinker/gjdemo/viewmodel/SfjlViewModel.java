package com.thinker.gjdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thinker.gjdemo.entity.SFGL;
import com.thinker.gjdemo.repository.SfjlRepository;

import java.util.List;

public class SfjlViewModel extends AndroidViewModel {
    private SfjlRepository sfjlRepository;

    public SfjlViewModel(@NonNull Application application) {
        super(application);
        sfjlRepository = new SfjlRepository(application);
    }

    public LiveData<List<SFGL>> getAll() {
        return sfjlRepository.getAll();
    }

    public List<SFGL> getAllList () {
        return sfjlRepository.getAllRksList();
    }
    public LiveData<List<SFGL>> find(String pattern) {
        return sfjlRepository.find(pattern);
    }
    public void insert(SFGL... sfjls){
        sfjlRepository.insert(sfjls);
    }
    public void deleteAll(SFGL... sfjls){
        sfjlRepository.deleteAll();
    }
    public void delete(SFGL... sfjls){
        sfjlRepository.delete(sfjls);
    }
}