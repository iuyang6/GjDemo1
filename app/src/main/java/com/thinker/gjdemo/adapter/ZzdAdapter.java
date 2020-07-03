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
import com.thinker.gjdemo.entity.ZZD;

public class ZzdAdapter extends ListAdapter<ZZD, ZzdAdapter.MyViewHodler> {

     public ZzdAdapter() {
        //列表数据差异化回调
        super(new DiffUtil.ItemCallback<ZZD>() {
            @Override
            public boolean areItemsTheSame(@NonNull ZZD oldItem, @NonNull ZZD newItem) {
                return oldItem.getId()==newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ZZD oldItem, @NonNull ZZD newItem) {
                //字符串比较用equals
                return (oldItem.getZzdArea().equals(newItem.getZzdArea())
                && oldItem.getZzdCBR().equals(newItem.getZzdCBR())
                && oldItem.getZzdLocation().equals(newItem.getZzdLocation())
                && oldItem.getZzdName().equals(newItem.getZzdName())
                && oldItem.getZzdGJ().equals(newItem.getZzdGJ())
                && oldItem.getZzdSL().equals(newItem.getZzdSL())
                );
            }
        });
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card_zzd,parent,false);
        return new MyViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        final ZZD zzd = getItem(position);
        holder.textView_ZZD.setText(zzd.getZzdName());
        holder.textView_Location.setText(zzd.getZzdLocation());
        holder.textView_CBR.setText(zzd.getZzdCBR());
        holder.textView_Area.setText(zzd.getZzdArea());
        holder.textView_GJPZ.setText(zzd.getZzdGJ());
        holder.textView_ZZSL.setText(zzd.getZzdSL());

    }
    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView textView_ZZD,textView_Location,textView_Area,textView_CBR,textView_GJPZ,textView_ZZSL;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView_ZZD = itemView.findViewById(R.id.textView75);
            textView_Location = itemView.findViewById(R.id.textView87);
            textView_Area = itemView.findViewById(R.id.textView79);
            textView_CBR = itemView.findViewById(R.id.textView81);
            textView_GJPZ = itemView.findViewById(R.id.textView83);
            textView_ZZSL = itemView.findViewById(R.id.textView85);
        }
    }
}
