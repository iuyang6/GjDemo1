package com.thinker.gjdemo.fragment;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thinker.gjdemo.R;
import com.thinker.gjdemo.viewmodel.RuKuViewModel;

public class RuKuFragment extends Fragment {

    private RuKuViewModel mViewModel;

    public static RuKuFragment newInstance() {
        return new RuKuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ru_ku_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();

        FloatingActionButton floatingActionButton = activity.findViewById(R.id.floatingActionButton_ruku);
        //添加入库记录按钮监听
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_ruKuFragment_to_xinJianRuKuFragment3);
            }
        });

    }

}