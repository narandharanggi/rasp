package com.cun.rasp.view.detailsapi;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.DetailSapi;

import java.text.NumberFormat;
import java.util.List;

 
public class DetailSapiAdapter extends RecyclerView.Adapter<DetailSapiAdapter.MyViewHolder> {
 
    private Context context;
    private List<DetailSapi> detailsapiList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {

        
        public TextView sapi;
        public TextView lemak_susu;
        public TextView perBB;
 
        public MyViewHolder(View view) {
            super(view);
            sapi = view.findViewById(R.id.sapi_detail_sapi_data_row_list);
            lemak_susu = view.findViewById(R.id.lemak_susu_detail_sapi_data_row_list);
            perBB = view.findViewById(R.id.perBB_detail_sapi_data_row_list);
        }
    }
 
 
    public DetailSapiAdapter(Context context, List<DetailSapi> detailsapiList) {
        this.context = context;
        this.detailsapiList = detailsapiList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_detail_sapi, parent, false);
 
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      DetailSapi sapi = detailsapiList.get(position);
      DetailSapi lemak_susu = detailsapiList.get(position);
      DetailSapi perBB = detailsapiList.get(position);


        holder.sapi.setText(nm.format(sapi.getSapi()));
        holder.lemak_susu.setText(nm.format(lemak_susu.getLemakSusu()));
        holder.perBB.setText(nm.format(perBB.getperBB()));
    
 
    }
 
    @Override
    public int getItemCount() {
        return detailsapiList.size();
    }
 
}
