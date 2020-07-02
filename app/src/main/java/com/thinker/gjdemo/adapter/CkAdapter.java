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

public class CkAdapter extends ListAdapter<CK, CkAdapter.MyViewHodler> {

     public CkAdapter() {
        //列表数据差异化回调
        super(new DiffUtil.ItemCallback<CK>() {
            @Override
            public boolean areItemsTheSame(@NonNull CK oldItem, @NonNull CK newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CK oldItem, @NonNull CK newItem) {
                //字符串比较用equals
                return (oldItem.getCkName().equals(newItem.getCkName())
                && oldItem.getCkDetail().equals(newItem.getCkDetail())
                && oldItem.getCkFuZeRen().equals(newItem.getCkFuZeRen())
                && oldItem.getCkKeeper().equals(newItem.getCkKeeper())
                && oldItem.getCkLocation().equals(newItem.getCkLocation())
                );
            }
        });
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_cangku,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final CK ck = getItem(position);
        holder.textView_CKMC.setText(ck.getCkName());
        holder.textView_CKDZ.setText(ck.getCkLocation());
        holder.textView_FZR.setText(ck.getCkFuZeRen());
        holder.textView_BGY.setText(ck.getCkKeeper());
        holder.textView_MS.setText(ck.getCkDetail());
    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_CKMC,textView_CKDZ,textView_FZR,textView_BGY,textView_MS;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_CKMC = itemView.findViewById(R.id.textView24);
            textView_CKDZ = itemView.findViewById(R.id.textView29);
            textView_FZR = itemView.findViewById(R.id.textView31);
            textView_BGY = itemView.findViewById(R.id.textView33);
            textView_MS = itemView.findViewById(R.id.textView27);
        }
    }
}
