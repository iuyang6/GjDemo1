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
import com.thinker.gjdemo.entity.CKJL;

public class CkjlAdapter extends ListAdapter<CKJL, CkjlAdapter.MyViewHodler> {

     public CkjlAdapter() {
        //列表数据差异化回调
        super(new DiffUtil.ItemCallback<CKJL>() {
            @Override
            public boolean areItemsTheSame(@NonNull CKJL oldItem, @NonNull CKJL newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CKJL oldItem, @NonNull CKJL newItem) {
                //字符串比较用equals
                return (oldItem.getCkName().equals(newItem.getCkName())
                && oldItem.getCkName().equals(newItem.getCkName())
                && oldItem.getCkSL().equals(newItem.getCkSL())
                && oldItem.getCkTime().equals(newItem.getCkTime())
                && oldItem.getLYR().equals(newItem.getLYR())
                && oldItem.getClName().equals(newItem.getClName())
                );
            }
        });
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_chuku,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final CKJL ckjl = getItem(position);
        holder.textView_CKMC.setText(ckjl.getCkName());
        holder.textView_CLMC.setText(ckjl.getClName());
        holder.textView_CKSL.setText(ckjl.getCkSL());
        holder.textView_CKSJ.setText(ckjl.getCkTime());
        holder.textView_LYR.setText(ckjl.getLYR());

    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_CKMC,textView_CLMC,textView_CKSJ,textView_CKSL,textView_LYR;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_CKMC = itemView.findViewById(R.id.textView66);
            textView_CLMC = itemView.findViewById(R.id.textView68);
            textView_CKSJ = itemView.findViewById(R.id.textView73);
            textView_CKSL = itemView.findViewById(R.id.textView70);
            textView_LYR = itemView.findViewById(R.id.textView74);
        }
    }
}
