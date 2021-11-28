package com.cun.rasp.view.sapi;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.Sapi;

import java.text.NumberFormat;
import java.util.List;

 
public class SapiAdapter extends RecyclerView.Adapter<SapiAdapter.MyViewHolder> {
 
    private Context context;
    private List<Sapi> sapiList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView bobot;
        public TextView produksi_susu;
        public TextView bk;
      
 
        public MyViewHolder(View view) {
            super(view);
            produksi_susu = view.findViewById(R.id.produksi_susu_sapi_data_row_list);
            bk = view.findViewById(R.id.bk_sapi_data_row_list);
            bobot = view.findViewById(R.id.bobot_sapi_data_row_list);
        }
    }
 
 
    public SapiAdapter(Context context, List<Sapi> sapiList) {
        this.context = context;
        this.sapiList = sapiList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_sapi, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      Sapi produksi_susu = sapiList.get(position);
      Sapi bk = sapiList.get(position);
      Sapi bobot = sapiList.get(position);

        holder.produksi_susu.setText(nm.format(produksi_susu.getProduksiSusu()));
        holder.bk.setText(nm.format(bk.getBk()));
        holder.bobot.setText(String.valueOf(bobot.getBobot()));
 
    }
 
    @Override
    public int getItemCount() {
        return sapiList.size();
    }
 
}
