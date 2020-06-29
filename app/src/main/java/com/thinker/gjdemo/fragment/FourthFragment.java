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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thinker.gjdemo.adapter.NyzfAdapter;
import com.thinker.gjdemo.dao.NYZFDao;
import com.thinker.gjdemo.database.NyzfDatabase;
import com.thinker.gjdemo.entity.NYZF;
import com.thinker.gjdemo.viewmodel.FourthViewModel;
import com.thinker.gjdemo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FourthFragment extends Fragment {
    Button button1,button2,button3,button4;
    TextView textView;
    private FourthViewModel fourthViewModel;
    RecyclerView recyclerView;
    NyzfAdapter nyzfAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


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
        fourthViewModel.getAllNyzfsLive().observe(getActivity(), new Observer<List<NYZF>>() {
            @Override
            public void onChanged(List<NYZF> nyzfs) {
                nyzfAdapter.setAllNyzf(nyzfs);
                nyzfAdapter.notifyDataSetChanged();
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loading();
            }
        });
//        button1 = view.findViewById(R.id.button);
//        button2 = view.findViewById(R.id.button2);
//        button3 = view.findViewById(R.id.button2);
//        button4 = view.findViewById(R.id.button4);
        //插入数据
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NYZF nyzf1 = new NYZF("aaa执法","小李","井研县农业局a","aaaaaaaaaa","2020-6-11");
//                NYZF nyzf2 = new NYZF("bbb执法","小王","井研县农业局b","bbbbbbbbbb","2020-6-12");
//                NYZF nyzf3 = new NYZF("ccc执法","小张","井研县农业局c","cccccccccc","2020-6-13");
//                NYZF nyzf4 = new NYZF("ddd执法","小将","井研县农业局d","dddddddddd","2020-6-14");
//                NYZF nyzf5 = new NYZF("eee执法","小刘","井研县农业局e","eeeeeeeeee","2020-6-15");
//                fourthViewModel.insert(nyzf1,nyzf2,nyzf3,nyzf4,nyzf5);
//            }
//        });

//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fourthViewModel.deleteAll();
//            }
//        });
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

    void loading(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    FormBody.Builder params = new FormBody.Builder();//带参数访问
                    OkHttpClient client = new OkHttpClient();//创建http客户端
                    Request request = new Request.Builder()
                            .url("http://192.168.62.187:8999/Nyzf/listById")
                            .get()
                            .build();//创造http请求
                    Response response = client.newCall(request).execute();//发送请求

                    String responseData = response.body().string();//获取返回的json格式的结果
                    //
                    if(null != response.body()){
                        fourthViewModel.deleteAll();
                    }
                    JSONArray jsonArray = new JSONArray(responseData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String jsonString = jsonObject.toString();
                        Gson gson = new Gson();
                        NYZF nyzf = gson.fromJson(jsonString,NYZF.class);

                        fourthViewModel.insert(nyzf);
//                        System.out.println(nyzf);
//                        Log.d("username",""+ jsonObject.getString("zUser"));
//                        Log.d("zname",""+ jsonObject.getString("zName"));
//                        Log.d("ztime",""+ jsonObject.getString("zTime"));
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(swipeRefreshLayout.isRefreshing()){
                                swipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(),"数据刷新成功！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(swipeRefreshLayout.isRefreshing()) {
                                swipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(), "网络连接失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(FourthViewModel.class);
        // TODO: Use the ViewModel
    }

}