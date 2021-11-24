package com.cun.rasp.view.detailpakan;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.DetailPakan;

import java.text.NumberFormat;
import java.util.List;

 
public class DetailPakanAdapter extends RecyclerView.Adapter<DetailPakanAdapter.MyViewHolder> {
 
    private Context context;
    private List<DetailPakan> detailPakanList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView sapi;
        public TextView bahan_pakan;
 
        public MyViewHolder(View view) {
            super(view);
            sapi = view.findViewById(R.id.sapi_detail_pakan_data_row_list);
            bahan_pakan = view.findViewById(R.id.bahan_pakan_detail_pakan_data_row_list);
        }
    }
 
 
    public DetailPakanAdapter(Context context, List<DetailPakan> detailPakanList) {
        this.context = context;
        this.detailPakanList = detailPakanList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_detail_pakan, parent, false);
 
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      DetailPakan sapi = detailPakanList.get(position);
      DetailPakan bahan_pakan = detailPakanList.get(position);


        holder.sapi.setText(nm.format(sapi.getSapi()));
        holder.bahan_pakan.setText(nm.format(bahan_pakan.getBahanPakan()));
    
 
    }
 
    @Override
    public int getItemCount() {
        return detailPakanList.size();
    }
 
}
