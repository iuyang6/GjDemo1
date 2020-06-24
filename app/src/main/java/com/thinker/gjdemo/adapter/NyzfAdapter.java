package com.thinker.gjdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.NYZF;

import java.util.ArrayList;
import java.util.List;

public class NyzfAdapter extends RecyclerView.Adapter<NyzfAdapter.MyViewHodler> {
    List<NYZF> allNyzf = new ArrayList<>();

    public void setAllNyzf(List<NYZF> allNyzf) {
        this.allNyzf = allNyzf;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final NYZF nyzf = allNyzf.get(position);
        holder.textView_zname.setText(nyzf.getzName());
        holder.textView_ztime.setText(nyzf.getzTime());
        holder.textView_zdetail.setText(nyzf.getzDetail());
        holder.textView_zuser.setText(nyzf.getzUser());
        holder.click_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击查看详情的事件
            }
        });

        holder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击小叉叉的事件
//                nyzfRepository.deleteAll();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allNyzf.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_zname,textView_zuser,textView_ztime,textView_zdetail,click_view;
        ImageView delete_img;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_zname = itemView.findViewById(R.id.z_name);
            textView_zuser = itemView.findViewById(R.id.z_user);
            textView_ztime = itemView.findViewById(R.id.z_time);
            textView_zdetail = itemView.findViewById(R.id.z_detail);
            click_view = itemView.findViewById(R.id.click_detail);
            delete_img = itemView.findViewById(R.id.imageDelete);
        }
    }
}
