package com.thinker.gjdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.thinker.gjdemo.R;
import com.thinker.gjdemo.entity.CK;
import com.thinker.gjdemo.entity.RKJL;

public class RkjlAdapter extends ListAdapter<RKJL, RkjlAdapter.MyViewHodler> {

     public RkjlAdapter() {
        //列表数据差异化回调
        super(new DiffUtil.ItemCallback<RKJL>() {
            @Override
            public boolean areItemsTheSame(@NonNull RKJL oldItem, @NonNull RKJL newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull RKJL oldItem, @NonNull RKJL newItem) {
                //字符串比较用equals
                return (oldItem.getCkName().equals(newItem.getCkName())
                && oldItem.getClBZQ().equals(newItem.getClBZQ())
                && oldItem.getClCGR().equals(newItem.getClCGR())
                && oldItem.getClDanJia().equals(newItem.getClDanJia())
                && oldItem.getClYSR().equals(newItem.getClYSR())
                && oldItem.getClZJE().equals(newItem.getClZJE())
                && oldItem.getRkTime().equals(newItem.getRkTime())
                );
            }
        });
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_ruku,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final RKJL rkjl = getItem(position);
        holder.textView_CKMC.setText(rkjl.getCkName());
        holder.textView_CLMC.setText(rkjl.getClName());
        holder.textView_BZQ.setText(rkjl.getClBZQ());
        holder.textView_CGR.setText(rkjl.getClCGR());
        holder.textView_DJ.setText(rkjl.getClDanJia());
        holder.textView_RKSJ.setText(rkjl.getRkTime());
        holder.textView_YSR.setText(rkjl.getClYSR());
        holder.textView_ZJE.setText(rkjl.getClZJE());

    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_CKMC,textView_CLMC,textView_DJ,textView_CGR,textView_BZQ,textView_RKSJ,textView_ZJE,textView_YSR;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_CKMC = itemView.findViewById(R.id.textView46);
            textView_CLMC = itemView.findViewById(R.id.textView49);
            textView_DJ = itemView.findViewById(R.id.textView50);
            textView_CGR = itemView.findViewById(R.id.textView52);
            textView_BZQ = itemView.findViewById(R.id.textView54);
            textView_RKSJ = itemView.findViewById(R.id.textView58);
            textView_ZJE = itemView.findViewById(R.id.textView56);
            textView_YSR = itemView.findViewById(R.id.textView60);
        }
    }
}
