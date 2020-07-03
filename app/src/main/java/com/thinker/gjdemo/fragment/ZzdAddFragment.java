package com.thinker.gjdemo.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.CKJL;
import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.entity.ZZD;
import com.thinker.gjdemo.viewmodel.CangKuViewModel;
import com.thinker.gjdemo.viewmodel.ZzdGlViewModel;
import com.thinker.gjdemo.viewmodel.ClglViewModel;

import java.util.List;

public class ZzdAddFragment extends Fragment {


    private ZzdGlViewModel zzdGlViewModel;
    private FragmentActivity activity;

    private Button buttonCancel,buttonSubmit;
    private EditText editTextZZD,editTextZZDLocation,editTextZZDArea,editTextCBR,editTextGJPZ,editTextZZSL;



    public static ZzdAddFragment newInstance() {
        return new ZzdAddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zzd_add_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = requireActivity();

        zzdGlViewModel = new ViewModelProvider(activity).get(ZzdGlViewModel.class);

        editTextZZD = activity.findViewById(R.id.editTextTextPersonName15);
        editTextZZDLocation = activity.findViewById(R.id.editTextTextPersonName11);
        editTextZZDArea = activity.findViewById(R.id.editTextTextPersonName10);
        editTextCBR = activity.findViewById(R.id.editTextTextPersonName13);
        editTextGJPZ = activity.findViewById(R.id.editTextTextPersonName14);
        editTextZZSL = activity.findViewById(R.id.editTextTextPersonName12);

        buttonSubmit = activity.findViewById(R.id.button14);
        buttonCancel = activity.findViewById(R.id.button13);

        //初次进入，确认按钮设置为不可用状态
        buttonSubmit.setEnabled(false);

        //editext输入监听
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String zzdname = editTextZZD.getText().toString().trim();
                String location = editTextZZDLocation.getText().toString().trim();
                String area = editTextZZDArea.getText().toString().trim();
                String cbr = editTextCBR.getText().toString().trim();
                String gjpz = editTextGJPZ.getText().toString().trim();
                String zzsl = editTextZZSL.getText().toString().trim();
                //都不为空时，确认按钮有效
                buttonSubmit.setEnabled(!zzdname.isEmpty() && !location.isEmpty()
                        && !cbr.isEmpty()&& !gjpz.isEmpty() && !zzsl.isEmpty() && !area.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        //每一个输入栏添加监听
        editTextZZD.addTextChangedListener(textWatcher);
        editTextZZDLocation.addTextChangedListener(textWatcher);
        editTextZZDArea.addTextChangedListener(textWatcher);
        editTextCBR.addTextChangedListener(textWatcher);
        editTextGJPZ.addTextChangedListener(textWatcher);
        editTextZZSL.addTextChangedListener(textWatcher);
        //为确认按钮添加事件
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zzdname = editTextZZD.getText().toString().trim();
                String location = editTextZZDLocation.getText().toString().trim();
                String area = editTextZZDArea.getText().toString().trim();
                String cbr = editTextCBR.getText().toString().trim();
                String gjpz = editTextGJPZ.getText().toString().trim();
                String zzsl = editTextZZSL.getText().toString().trim();
                ZZD zzd = new ZZD(zzdname,location,area,cbr,gjpz,zzsl);
                //呼叫出库记录repository的添加方法
                zzdGlViewModel.insert(zzd);
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