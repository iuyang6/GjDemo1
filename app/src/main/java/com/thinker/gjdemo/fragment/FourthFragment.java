package com.thinker.gjdemo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thinker.gjdemo.adapter.NyzfAdapter;
import com.thinker.gjdemo.dao.NYZFDao;
import com.thinker.gjdemo.database.NyzfDatabase;
import com.thinker.gjdemo.entity.NYZF;
import com.thinker.gjdemo.viewmodel.FourthViewModel;
import com.thinker.gjdemo.R;

import java.util.List;

public class FourthFragment extends Fragment {
    Button button1,button2,button3,button4;
    TextView textView;
    private FourthViewModel fourthViewModel;
    RecyclerView recyclerView;
    NyzfAdapter nyzfAdapter;


    public static FourthFragment newInstance() {
        return new FourthFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourth_fragment, container, false);
        fourthViewModel = new ViewModelProvider(this).get(FourthViewModel.class);
        recyclerView = view.findViewById(R.id.nyzfList);
        nyzfAdapter = new NyzfAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(nyzfAdapter);

        button1 = view.findViewById(R.id.button);
//        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button2);
//        button4 = view.findViewById(R.id.button4);
        fourthViewModel.getAllNyzfsLive().observe(getActivity(), new Observer<List<NYZF>>() {
            @Override
            public void onChanged(List<NYZF> nyzfs) {
                nyzfAdapter.setAllNyzf(nyzfs);
                nyzfAdapter.notifyDataSetChanged();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NYZF nyzf1 = new NYZF("aaa执法","小李","井研县农业局a","aaaaaaaaaa","2020-6-11");
                NYZF nyzf2 = new NYZF("bbb执法","小王","井研县农业局b","bbbbbbbbbb","2020-6-12");
                NYZF nyzf3 = new NYZF("ccc执法","小张","井研县农业局c","cccccccccc","2020-6-13");
                NYZF nyzf4 = new NYZF("ddd执法","小将","井研县农业局d","dddddddddd","2020-6-14");
                NYZF nyzf5 = new NYZF("eee执法","小刘","井研县农业局e","eeeeeeeeee","2020-6-15");
                fourthViewModel.insert(nyzf1,nyzf2,nyzf3,nyzf4,nyzf5);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthViewModel.deleteAll();
            }
        });
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NYZF nyzf = new NYZF("bbb执法","小张","井研县农业局","xxxxxxx","2020-6-11");
//                nyzf.setId(300);
//                fourthViewModel.delete(nyzf);
//            }
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(FourthViewModel.class);
        // TODO: Use the ViewModel
    }

}