package com.thinker.gjdemo.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.viewmodel.CangKuViewModel;

public class CangKuTianJiaFragment extends Fragment {
    private CangKuViewModel cangKuViewModel;
    private Button buttonCancel,buttonSubmit;
    private EditText editTextCKMC,editTextCKDZ,editTextFZR,editTextBGY,editTextMS;

    public static CangKuTianJiaFragment newInstance() {
        return new CangKuTianJiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cang_ku_tian_jia_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();
        cangKuViewModel = new ViewModelProvider(activity).get(CangKuViewModel.class);

        editTextCKMC = activity.findViewById(R.id.editTextTextPersonName);
        editTextCKDZ = activity.findViewById(R.id.editTextTextPersonName2);
        editTextFZR = activity.findViewById(R.id.editTextTextPersonName3);
        editTextBGY = activity.findViewById(R.id.editTextTextPersonName4);
        editTextMS = activity.findViewById(R.id.add_content);
        buttonCancel = activity.findViewById(R.id.button3);
        buttonSubmit = activity.findViewById(R.id.button6);

        //初次进入，确认按钮设置为不可用状态
        buttonSubmit.setEnabled(false);

        //获取焦点
        editTextCKMC.requestFocus();
        //弹出键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextCKMC,0);

        //editext输入监听
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ckmc = editTextCKMC.getText().toString().trim();
                String ckdz = editTextCKDZ.getText().toString().trim();
                String fzr = editTextFZR.getText().toString().trim();
                String ms = editTextMS.getText().toString().trim();
                String bgy = editTextBGY.getText().toString().trim();
                //都不为空时，确认按钮有效
                buttonSubmit.setEnabled(!ckmc.isEmpty() && !ckdz.isEmpty() && !fzr.isEmpty() && !ms.isEmpty() && !bgy.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        //每一个输入栏添加监听
        editTextCKMC.addTextChangedListener(textWatcher);
        editTextCKDZ.addTextChangedListener(textWatcher);
        editTextFZR.addTextChangedListener(textWatcher);
        editTextMS.addTextChangedListener(textWatcher);
        editTextBGY.addTextChangedListener(textWatcher);
        //为确认按钮添加事件
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ckmc = editTextCKMC.getText().toString().trim();
                String ckdz = editTextCKDZ.getText().toString().trim();
                String fzr = editTextFZR.getText().toString().trim();
                String ms = editTextMS.getText().toString().trim();
                String bgy = editTextBGY.getText().toString().trim();
                CK ck = new CK(ckmc,ckdz,ms,fzr,bgy);
                //呼叫材料repository的添加方法
                cangKuViewModel.insert(ck);
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




        // TODO: Use the ViewModel
    }

}