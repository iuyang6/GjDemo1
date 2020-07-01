package com.thinker.gjdemo.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.viewmodel.CangKuViewModel;

import java.util.List;

public class CangKuFragment extends Fragment {

    private CangKuViewModel mViewModel;

    public static CangKuFragment newInstance() {
        return new CangKuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cang_ku_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //增加搜索按钮
        inflater.inflate(R.menu.search_menu,menu);
        //取得搜索框的引用
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        //设置大小
        searchView.setMaxWidth(580);
        //搜索框内容添加监听事件
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                String pattern = newText.trim();
//                //在搜索之前移除观察
//                filteredCls.removeObservers(getViewLifecycleOwner());
//                filteredCls = clglViewModel.findClsLive(pattern);
//                filteredCls.observe(getViewLifecycleOwner(), new Observer<List<CLGL>>() {
//                    @Override
//                    public void onChanged(List<CLGL> clgls) {
//                        allCls = clgls;
//                        int temp = clAdapter.getItemCount();
//                        if(temp != clgls.size()){
//                            clAdapter.submitList(clgls);
//                        }
//                    }
//                });
//                return true;
//            }
//        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //显示搜索按钮
        setHasOptionsMenu(true);


    }

}