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
import com.thinker.gjdemo.viewmodel.KcglViewModel;

public class KcglFragment extends Fragment {

    private KcglViewModel mViewModel;

    public static KcglFragment newInstance() {
        return new KcglFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kcgl_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KcglViewModel.class);
        // TODO: Use the ViewModel
    }

}