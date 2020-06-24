package com.thinker.gjdemo.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.thinker.gjdemo.utils.MD5Utils;
import com.thinker.gjdemo.viewmodel.FirstViewModel;
import com.thinker.gjdemo.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FirstFragment extends Fragment {

    TextView text1,text2;
    Button button;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    private FirstViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        text1 = view.findViewById(R.id.editTextTextPersonName);
        text2 = view.findViewById(R.id.textView3);
        button = view.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String s = text1.getText().toString();
                    String sMd5 = MD5Utils.md5(s);
                    text2.setText(sMd5);
                    System.out.println(sMd5);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel
    }

}