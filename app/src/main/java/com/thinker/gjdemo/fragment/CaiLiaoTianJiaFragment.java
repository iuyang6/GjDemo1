package com.thinker.gjdemo.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.viewmodel.ClglViewModel;

public class CaiLiaoTianJiaFragment extends Fragment {
    private ClglViewModel clglViewModel;

    private Button buttonCancel,buttonSubmit;

    private EditText editTextCLMC,editTextSYZT,editTextJLDW;


    public static CaiLiaoTianJiaFragment newInstance() {
        return new CaiLiaoTianJiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cai_liao_tian_jia_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();

        //呼叫viewmodel进行数据的处理
        clglViewModel = new ViewModelProvider(activity).get(ClglViewModel.class);
        editTextCLMC = activity.findViewById(R.id.editTextTextClmc);
        editTextJLDW = activity.findViewById(R.id.editTextTextJldw);
        editTextSYZT = activity.findViewById(R.id.editTextTextSyzt);
        buttonCancel = activity.findViewById(R.id.button2);
        buttonSubmit = activity.findViewById(R.id.button);

        //初次进入，确认按钮设置为不可用状态
        buttonSubmit.setEnabled(false);

        //获取焦点
        editTextCLMC.requestFocus();
        //弹出键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextCLMC,0);

        //editext输入监听
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String clmc = editTextCLMC.getText().toString().trim();
                String jldw = editTextJLDW.getText().toString().trim();
                String syzt = editTextSYZT.getText().toString().trim();
                //都不为空时，确认按钮有效
                buttonSubmit.setEnabled(!clmc.isEmpty() && !jldw.isEmpty() && !syzt.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        //每一个输入栏添加监听
        editTextCLMC.addTextChangedListener(textWatcher);
        editTextSYZT.addTextChangedListener(textWatcher);
        editTextJLDW.addTextChangedListener(textWatcher);
        //为确认按钮添加事件
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clmc = editTextCLMC.getText().toString().trim();
                String jldw = editTextJLDW.getText().toString().trim();
                String syzt = editTextSYZT.getText().toString().trim();
                CLGL clgl = new CLGL(clmc,jldw,syzt);
                //呼叫材料repository的添加方法
                clglViewModel.insert(clgl);
                //呼叫返回
                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();
            }
        });
        //取消按钮事件
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //呼叫返回
                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();
            }
        });




//        mViewModel = ViewModelProviders.of(this).get(CaiLiaoTianJiaViewModel.class);
        // TODO: Use the ViewModel
    }

}