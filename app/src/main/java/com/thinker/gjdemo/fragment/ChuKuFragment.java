package com.thinker.gjdemo.fragment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.thinker.gjdemo.R;
import com.thinker.gjdemo.adapter.CkjlAdapter;
import com.thinker.gjdemo.entity.CKJL;
import com.thinker.gjdemo.viewmodel.ChuKuViewModel;
import com.thinker.gjdemo.viewmodel.ChuKuViewModel;

import java.util.List;

public class ChuKuFragment extends Fragment {


    private ChuKuViewModel chuKuViewModel;
    private RecyclerView recyclerView;

    private List<CKJL> allCks;
    private boolean undoAction;
    private CkjlAdapter ckjlAdapter;
    //过滤后的数据
    private LiveData<List<CKJL>> filteredCks;


    public static RuKuFragment newInstance() {
        return new RuKuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chu_ku_fragment, container, false);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pattern = newText.trim();
                //在搜索之前移除观察
                filteredCks.removeObservers(getViewLifecycleOwner());
                filteredCks = chuKuViewModel.find(pattern);
                filteredCks.observe(getViewLifecycleOwner(), new Observer<List<CKJL>>() {
                    @Override
                    public void onChanged(List<CKJL> ckjls) {
                        allCks = ckjls;
                        int temp = ckjlAdapter.getItemCount();
                        if(temp != ckjls.size()){
                            ckjlAdapter.submitList(ckjls);
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        //显示搜索按钮
        setHasOptionsMenu(true);
        FloatingActionButton floatingActionButton = activity.findViewById(R.id.floatingActionButton);
        //添加入库记录按钮监听
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_chuKuFragment_to_xinJianChuKuFragment2);
            }
        });

        chuKuViewModel = new ViewModelProvider(activity).get(ChuKuViewModel.class);
        recyclerView = activity.findViewById(R.id.chukuList);
        ckjlAdapter = new CkjlAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(ckjlAdapter);
        //一开始将全部赋值给过滤后的数据
        filteredCks = chuKuViewModel.getAll();
        //监听材料的变化，页面刷新
        filteredCks.observe(getViewLifecycleOwner(), new Observer<List<CKJL>>() {
            @Override
            public void onChanged(List<CKJL> ckjls) {
                allCks = ckjls;
                int temp = ckjlAdapter.getItemCount();
                if(temp != ckjls.size()){
                    if (temp < ckjls.size() && !undoAction) {
                        recyclerView.smoothScrollBy(0,-200);
                    }
                    undoAction = false;
                    ckjlAdapter.submitList(ckjls);
                }

            }
        });
        //滑动删除
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START | ItemTouchHelper.END    ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final CKJL ckjlToDelete = allCks.get(viewHolder.getAdapterPosition());
                chuKuViewModel.delete(ckjlToDelete);

                //撤销删除
                Snackbar.make(requireActivity().findViewById(R.id.chuku_fragment),"删除了一条出库记录",Snackbar.LENGTH_SHORT)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                undoAction = true;
                                chuKuViewModel.insert(ckjlToDelete);
                            }
                        })
                        .show();
            }

            //在滑动的时候，画出浅灰色背景和垃圾桶图标，增强删除的视觉效果
            Drawable icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_baseline_delete_forever_24);
            Drawable background = new ColorDrawable(Color.LTGRAY);
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;

                int iconLeft,iconRight,iconTop,iconBottom;
                int backTop,backBottom,backLeft,backRight;
                backTop = itemView.getTop();
                backBottom = itemView.getBottom();
                iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) /2;
                iconBottom = iconTop + icon.getIntrinsicHeight();
                if (dX > 0) {
                    backLeft = itemView.getLeft();
                    backRight = itemView.getLeft() + (int)dX;
                    background.setBounds(backLeft,backTop,backRight,backBottom);
                    iconLeft = itemView.getLeft() + iconMargin ;
                    iconRight = iconLeft + icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                } else if (dX < 0){
                    backRight = itemView.getRight();
                    backLeft = itemView.getRight() + (int)dX;
                    background.setBounds(backLeft,backTop,backRight,backBottom);
                    iconRight = itemView.getRight()  - iconMargin;
                    iconLeft = iconRight - icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                } else {
                    background.setBounds(0,0,0,0);
                    icon.setBounds(0,0,0,0);
                }
                background.draw(c);
                icon.draw(c);
            }
        }).attachToRecyclerView(recyclerView);

    }
    @Override
    public void onResume() {
        //当页面返回时隐藏键盘
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
        super.onResume();
    }

}