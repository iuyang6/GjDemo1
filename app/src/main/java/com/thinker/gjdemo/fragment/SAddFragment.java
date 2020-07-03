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
import com.thinker.gjdemo.entity.SFGL;
import com.thinker.gjdemo.entity.ZZD;
import com.thinker.gjdemo.viewmodel.ZzdGlViewModel;
import com.thinker.gjdemo.viewmodel.ChuKuViewModel;
import com.thinker.gjdemo.viewmodel.SfjlViewModel;
import com.thinker.gjdemo.viewmodel.SAddViewModel;

import java.util.List;

public class SAddFragment extends Fragment {


    private SfjlViewModel sfjllViewModel;
    private ZzdGlViewModel zzdGlViewModel;
    private List<ZZD> allZzds;
    private FragmentActivity activity;
    //保存选择的种植地名称
    private String zzdMc;

    private Button buttonCancel,buttonSubmit;
    private EditText editTextZZD,editTextFLMC,editTextSFSL,editTextSFTime;
    //存放种植地名称
    private String[] m;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;


    public static SAddFragment newInstance() {
        return new SAddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.s_add_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = requireActivity();

        zzdGlViewModel = new ViewModelProvider(activity).get(ZzdGlViewModel.class);
        sfjllViewModel = new ViewModelProvider(activity).get(SfjlViewModel.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if(zzdGlViewModel.getAllRkList()!=null){
                        allZzds = zzdGlViewModel.getAllRkList();
                        m = new String[allZzds.size()];
                        for (int i = 0; i < allZzds.size() ; i++) {
                            m[i] = allZzds.get(i).getZzdName();
                        }
                    }
                    spinner = activity.findViewById(R.id.spinner5);
                    adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, m);
                    //设置下拉列表的风格
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_list_item_checked
                    //        int dp2px = Utils.dp2px(mContext, 40);
                    //        mSpinner.setDropDownVerticalOffset(dp2px); //这个是第二种方式，防止下拉框遮挡显示框的办法
                    //设置下拉选择框的背景
//        spinner.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.edit_background));
                    //将adapter 添加到spinner中
                    spinner.setAdapter(adapter);
                    //添加事件Spinner事件监听
                    spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
                }catch (Exception e){
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "数据库查询失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();


        editTextFLMC = activity.findViewById(R.id.editTextTextPersonName16);
        editTextSFSL = activity.findViewById(R.id.editTextTextPersonName17);
        editTextSFTime = activity.findViewById(R.id.editTextTextPersonName18);
        buttonSubmit = activity.findViewById(R.id.button18);
        buttonCancel = activity.findViewById(R.id.button17);

        //初次进入，确认按钮设置为不可用状态
        buttonSubmit.setEnabled(false);

        //editext输入监听
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String flmc = editTextFLMC.getText().toString().trim();
                String sfsl = editTextSFSL.getText().toString().trim();
                String sfTime = editTextSFTime.getText().toString().trim();
                //都不为空时，确认按钮有效
                buttonSubmit.setEnabled(!flmc.isEmpty() && !sfsl.isEmpty() && !sfTime.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        //每一个输入栏添加监听
        editTextFLMC.addTextChangedListener(textWatcher);
        editTextSFSL.addTextChangedListener(textWatcher);
        editTextSFTime.addTextChangedListener(textWatcher);
        //为确认按钮添加事件
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flmc = editTextFLMC.getText().toString().trim();
                String sfsl = editTextSFSL.getText().toString().trim();
                String sfTime = editTextSFTime.getText().toString().trim();
                SFGL sfgl = new SFGL(zzdMc,flmc,sfsl,sfTime);
                //呼叫出库记录repository的添加方法
                sfjllViewModel.insert(sfgl);
                //            zzdGlViewModel.insert(ck);
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
    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String str = (String) spinner.getItemAtPosition(arg2);
            zzdMc = str;

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}