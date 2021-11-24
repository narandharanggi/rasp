package com.cun.rasp.view.lemaksusu;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.LemakSusu;

import java.text.NumberFormat;
import java.util.List;

 
public class LemakSusuAdapter extends RecyclerView.Adapter<LemakSusuAdapter.MyViewHolder> {
 
    private Context context;
    private List<LemakSusu> lemaksusuList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
  
       
        public TextView tdn;
        public TextView pk;
        public TextView ca;
        public TextView p;
        public TextView persen_lemak;
        
        public MyViewHolder(View view) {
            super(view);
            persen_lemak = view.findViewById(R.id.persen_lemak_lemak_susu_data_row_list);
            tdn = view.findViewById(R.id.tdn_lemak_susu_data_row_list);
            pk = view.findViewById(R.id.pk_lemak_susu_data_row_list);
            ca = view.findViewById(R.id.ca_lemak_susu_data_row_list);
            p = view.findViewById(R.id.p_lemak_susu_data_row_list);
        }
    }
 
 
    public LemakSusuAdapter(Context context, List<LemakSusu> lemaksusuList) {
        this.context = context;
        this.lemaksusuList = lemaksusuList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_lemak_susu, parent, false);
 
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      LemakSusu persen_lemak = lemaksusuList.get(position);
      LemakSusu tdn = lemaksusuList.get(position);
      LemakSusu pk = lemaksusuList.get(position);
      LemakSusu ca = lemaksusuList.get(position);
      LemakSusu p = lemaksusuList.get(position);

        holder.persen_lemak.setText(nm.format(persen_lemak.getPersenLemak()));
        holder.tdn.setText(nm.format(tdn.getTdn()));
        holder.pk.setText(nm.format(pk.getPk()));
        holder.ca.setText(nm.format(ca.getCa()));
        holder.p.setText(nm.format(p.getP()));
    
 
    }
 
    @Override
    public int getItemCount() {
        return lemaksusuList.size();
    }
 
}
