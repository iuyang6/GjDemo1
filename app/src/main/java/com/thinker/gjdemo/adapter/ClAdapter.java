package com.thinker.gjdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CLGL;
import com.thinker.gjdemo.entity.NYZF;

import java.util.ArrayList;
import java.util.List;

public class ClAdapter extends ListAdapter<CLGL,ClAdapter.MyViewHodler> {

     public ClAdapter() {
        //列表数据差异化回调
        super(new DiffUtil.ItemCallback<CLGL>() {
            @Override
            public boolean areItemsTheSame(@NonNull CLGL oldItem, @NonNull CLGL newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CLGL oldItem, @NonNull CLGL newItem) {
                //字符串比较用equals
                return (oldItem.getClmc().equals(newItem.getClmc())
                && oldItem.getJldw().equals(newItem.getJldw())
                && oldItem.getSyzt().equals(newItem.getSyzt())
                );
            }
        });
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_cl,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final CLGL clgl = getItem(position);
        holder.textView_clmc.setText(clgl.getClmc());
        holder.textView_jldw.setText(clgl.getJldw());
        holder.textView_syzt.setText(clgl.getSyzt());
    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_clmc,textView_jldw,textView_syzt;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_clmc = itemView.findViewById(R.id.textView);
            textView_jldw = itemView.findViewById(R.id.textView23);
            textView_syzt = itemView.findViewById(R.id.textView22);
        }
    }
}
