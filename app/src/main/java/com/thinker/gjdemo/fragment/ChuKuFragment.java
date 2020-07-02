package com.thinker.gjdemo.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.viewmodel.ChuKuViewModel;

public class ChuKuFragment extends Fragment {

    private ChuKuViewModel mViewModel;

    public static ChuKuFragment newInstance() {
        return new ChuKuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chu_ku_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChuKuViewModel.class);
        // TODO: Use the ViewModel
    }

}