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

import com.google.gson.Gson;
import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.entity.NYZF;
import com.thinker.gjdemo.viewmodel.CangKuViewModel;
import com.thinker.gjdemo.viewmodel.ClglViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XinJianRuKuFragment extends Fragment {

    private ClglViewModel clglViewModel;
    private CangKuViewModel cangKuViewModel;
    private List<CLGL> allCls;
    private List<CK> allCks;
    private FragmentActivity activity;

    private Button buttonCancel,buttonSubmit;
    private EditText editTextDJ,editTextZJE,editTextBZQ,editTextRKSJ,editTextCKR,editTextYSR;

    private String[] m;
    private String[] m2;
    private Spinner spinner,spinner2;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;


    public static XinJianRuKuFragment newInstance() {
        return new XinJianRuKuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xjru_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = requireActivity();

        cangKuViewModel = new ViewModelProvider(activity).get(CangKuViewModel.class);
        clglViewModel = new ViewModelProvider(activity).get(ClglViewModel.class);

        editTextDJ = activity.findViewById(R.id.editTextNumber2);
        editTextZJE = activity.findViewById(R.id.editTextNumber3);
        editTextBZQ = activity.findViewById(R.id.editTextNumber4);
        editTextRKSJ = activity.findViewById(R.id.editTextDate);
        editTextCKR = activity.findViewById(R.id.editTextTextPersonName6);
        editTextYSR = activity.findViewById(R.id.editTextTextPersonName5);

        //初次进入，确认按钮设置为不可用状态
        buttonSubmit.setEnabled(false);

        //editext输入监听
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cldj = editTextDJ.getText().toString().trim();
                String clzje = editTextZJE.getText().toString().trim();
                String clbzq = editTextBZQ.getText().toString().trim();
                String clrusj = editTextRKSJ.getText().toString().trim();
                String cgr = editTextCKR.getText().toString().trim();
                String ysr = editTextYSR.getText().toString().trim();
                //都不为空时，确认按钮有效
                buttonSubmit.setEnabled(!cldj.isEmpty() && !clzje.isEmpty() && !clbzq.isEmpty() && !clrusj.isEmpty() && !cgr.isEmpty() && !ysr.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        //每一个输入栏添加监听
        editTextDJ.addTextChangedListener(textWatcher);
        editTextZJE.addTextChangedListener(textWatcher);
        editTextBZQ.addTextChangedListener(textWatcher);
        editTextRKSJ.addTextChangedListener(textWatcher);
        editTextCKR.addTextChangedListener(textWatcher);
        editTextYSR.addTextChangedListener(textWatcher);
        //为确认按钮添加事件
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String cldj = editTextDJ.getText().toString().trim();
            String clzje = editTextZJE.getText().toString().trim();
            String clbzq = editTextBZQ.getText().toString().trim();
            String clrusj = editTextRKSJ.getText().toString().trim();
            String cgr = editTextCKR.getText().toString().trim();
            String ysr = editTextYSR.getText().toString().trim();
//            CK ck = new CK(ckmc,ckdz,ms,fzr,bgy);
            //呼叫材料repository的添加方法
//            cangKuViewModel.insert(ck);
            //呼叫返回
            NavController navController = Navigation.findNavController(v);
            navController.navigateUp();
            }
        });

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
                    spinner = activity.findViewById(R.id.spinner);
                    spinner2 = activity.findViewById(R.id.spinner2);
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




        // TODO: Use the ViewModel
    }
    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String str = (String) spinner.getItemAtPosition(arg2);

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    class SpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String str = (String) spinner2.getItemAtPosition(arg2);

//            System.out.println("材料" + str);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}