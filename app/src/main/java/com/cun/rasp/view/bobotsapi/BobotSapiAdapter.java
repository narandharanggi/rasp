package com.cun.rasp.view.bobotsapi;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.BobotSapi;

import java.text.NumberFormat;
import java.util.List;

 
public class BobotSapiAdapter extends RecyclerView.Adapter<BobotSapiAdapter.MyViewHolder> {
 
    private Context context;
    private List<BobotSapi> bobotSapiList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // public TextView note;
        // public TextView dot;
        // public TextView timestamp;

        public TextView bobot;
        public TextView tdn;
        public TextView pk;
        public TextView ca;
        public TextView p;
 
        public MyViewHolder(View view) {
            super(view);
            bobot = view.findViewById(R.id.bobot_bobot_sapi_data_row_list);
            tdn = view.findViewById(R.id.tdn_bobot_sapi_data_row_list);
            pk = view.findViewById(R.id.pk_bobot_sapi_data_row_list);
            ca = view.findViewById(R.id.ca_bobot_sapi_data_row_list);
            p = view.findViewById(R.id.p_bobot_sapi_data_row_list);
        }
    }
 
 
    public BobotSapiAdapter(Context context, List<BobotSapi> bobotSapiList) {
        this.context = context;
        this.bobotSapiList = bobotSapiList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_bobot_sapi, parent, false);
 
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      BobotSapi bobot = bobotSapiList.get(position);
      BobotSapi tdn = bobotSapiList.get(position);
      BobotSapi pk = bobotSapiList.get(position);
      BobotSapi ca = bobotSapiList.get(position);
      BobotSapi p = bobotSapiList.get(position);

        holder.bobot.setText(nm.format(bobot.getBobot()));
        holder.tdn.setText(nm.format(tdn.getTdn()));
        holder.pk.setText(nm.format(pk.getPk()));
        holder.ca.setText(nm.format(ca.getCa()));
        holder.p.setText(nm.format(p.getP()));
    
 
    }
 
    @Override
    public int getItemCount() {
        return bobotSapiList.size();
    }
 
}
