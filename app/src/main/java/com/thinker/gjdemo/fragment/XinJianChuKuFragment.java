package com.thinker.gjdemo.fragment;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.CKJL;
import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.entity.RKJL;
import com.thinker.gjdemo.viewmodel.CangKuViewModel;
import com.thinker.gjdemo.viewmodel.ClglViewModel;
import com.thinker.gjdemo.viewmodel.ChuKuViewModel;

import java.util.List;

public class XinJianChuKuFragment extends Fragment {

    private ClglViewModel clglViewModel;
    private CangKuViewModel cangKuViewModel;
    private ChuKuViewModel chuKuViewModel;
    private List<CLGL> allCls;
    private List<CK> allCks;
    private FragmentActivity activity;
    //保存选择的仓库名称和材料名称
    private String ckmc,clmc;

    private Button buttonCancel,buttonSubmit;
    private EditText editTextCKSJ,editTextChuKuSL,editTextLYR;

    private String[] m;
    private String[] m2;
    private Spinner spinner,spinner2;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;


    public static XinJianChuKuFragment newInstance() {
        return new XinJianChuKuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xjcu_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = requireActivity();

        cangKuViewModel = new ViewModelProvider(activity).get(CangKuViewModel.class);
        clglViewModel = new ViewModelProvider(activity).get(ClglViewModel.class);
        chuKuViewModel = new ViewModelProvider(activity).get(ChuKuViewModel.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if(cangKuViewModel.getAllList()!=null){
                        allCks = cangKuViewModel.getAllList();
                        m = new String[allCks.size()];
                        for (int i = 0; i < allCks.size() ; i++) {
                            m[i] = allCks.get(i).getCkName();
                        }
                    }
                    if(clglViewModel.getAllClsList()!=null){
                        allCls = clglViewModel.getAllClsList();
                        m2 = new String[allCls.size()];
                        for (int i = 0; i < allCls.size() ; i++) {
                            m2[i] = allCls.get(i).getClmc();
                        }
                    }
                    spinner = activity.findViewById(R.id.spinner3);
                    spinner2 = activity.findViewById(R.id.spinner4);
                    adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, m);
                    adapter2 = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, m2);
                    //设置下拉列表的风格
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_list_item_checked
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//simple_list_item_checked
                    //        int dp2px = Utils.dp2px(mContext, 40);
                    //        mSpinner.setDropDownVerticalOffset(dp2px); //这个是第二种方式，防止下拉框遮挡显示框的办法
                    //设置下拉选择框的背景
//        spinner.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.edit_background));
                    //将adapter 添加到spinner中
                    spinner.setAdapter(adapter);
                    spinner2.setAdapter(adapter2);
                    //添加事件Spinner事件监听
                    spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
                    spinner2.setOnItemSelectedListener(new SpinnerSelectedListener2());
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


        editTextCKSJ = activity.findViewById(R.id.editTextTextPersonName7);
        editTextChuKuSL = activity.findViewById(R.id.editTextTextPersonName8);
        editTextLYR = activity.findViewById(R.id.editTextTextPersonName9);
        buttonSubmit = activity.findViewById(R.id.button10);
        buttonCancel = activity.findViewById(R.id.button9);

        //初次进入，确认按钮设置为不可用状态
        buttonSubmit.setEnabled(false);

        //editext输入监听
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cksj = editTextCKSJ.getText().toString().trim();
                String cksl = editTextChuKuSL.getText().toString().trim();
                String lyr = editTextLYR.getText().toString().trim();
                //都不为空时，确认按钮有效
                buttonSubmit.setEnabled(!cksj.isEmpty() && !cksl.isEmpty() && !lyr.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        //每一个输入栏添加监听
        editTextCKSJ.addTextChangedListener(textWatcher);
        editTextChuKuSL.addTextChangedListener(textWatcher);
        editTextLYR.addTextChangedListener(textWatcher);
        //为确认按钮添加事件
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cksj = editTextCKSJ.getText().toString().trim();
                String cksl = editTextChuKuSL.getText().toString().trim();
                String lyr = editTextLYR.getText().toString().trim();
                CKJL ckjl = new CKJL(ckmc,clmc,cksj,cksl,lyr);
                //呼叫出库记录repository的添加方法
                chuKuViewModel.insert(ckjl);
        //            cangKuViewModel.insert(ck);
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
            ckmc = str;

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    class SpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String str = (String) spinner2.getItemAtPosition(arg2);
            clmc = str;

//            System.out.println("材料" + str);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}