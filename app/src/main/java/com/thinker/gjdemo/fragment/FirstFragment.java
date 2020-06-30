package com.thinker.gjdemo.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.thinker.gjdemo.utils.MD5Utils;
import com.thinker.gjdemo.viewmodel.FirstViewModel;
import com.thinker.gjdemo.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FirstFragment extends Fragment {

    private NavController navController;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    private FirstViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        navController = Navigation.findNavController(view.findViewById(R.id.fragment2));
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) getActivity(),navController);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel
    }

}