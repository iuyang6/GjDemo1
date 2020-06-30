package com.thinker.gjdemo.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.viewmodel.GlViewModel;

public class GlFragment extends Fragment {

    private GlViewModel mViewModel;
    ImageButton imageButtonCKGL,imageButtonKCGL;

    public static GlFragment newInstance() {
        return new GlFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gl, container, false);

        imageButtonCKGL = view.findViewById(R.id.imageButton9);
        imageButtonKCGL = view.findViewById(R.id.imageButton5);

        imageButtonCKGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_glFragment_to_ckglFragment);
            }
        });

        imageButtonKCGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_glFragment_to_kcglFragment);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(GlViewModel.class);

        // TODO: Use the ViewModel
    }

}