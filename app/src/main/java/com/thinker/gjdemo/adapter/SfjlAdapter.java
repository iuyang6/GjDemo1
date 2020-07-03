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
import com.thinker.gjdemo.entity.SFGL;

public class SfjlAdapter extends ListAdapter<SFGL, SfjlAdapter.MyViewHodler> {

     public SfjlAdapter() {
        //列表数据差异化回调
        super(new DiffUtil.ItemCallback<SFGL>() {
            @Override
            public boolean areItemsTheSame(@NonNull SFGL oldItem, @NonNull SFGL newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull SFGL oldItem, @NonNull SFGL newItem) {
                //字符串比较用equals
                return (oldItem.getZzdName().equals(newItem.getZzdName())
                && oldItem.getFLMC().equals(newItem.getFLMC())
                && oldItem.getSFTime().equals(newItem.getSFTime())
                && oldItem.getFLSL().equals(newItem.getFLSL())
                );
            }
        });
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_sfjl,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final SFGL ckjl = getItem(position);
        holder.textView_zzd.setText(ckjl.getZzdName());
        holder.textView_SFMC.setText(ckjl.getFLMC());
        holder.textView_SFTime.setText(ckjl.getSFTime());
        holder.textView_FLSL.setText(ckjl.getFLSL());

    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_zzd,textView_SFMC,textView_FLSL,textView_SFTime;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_zzd = itemView.findViewById(R.id.textView93);
            textView_SFMC = itemView.findViewById(R.id.textView99);
            textView_FLSL = itemView.findViewById(R.id.textView96);
            textView_SFTime = itemView.findViewById(R.id.textView98);
        }
    }
}
