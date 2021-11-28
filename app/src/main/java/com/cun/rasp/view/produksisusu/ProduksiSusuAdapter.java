package com.cun.rasp.view.produksisusu;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.ProduksiSusu;

import java.text.NumberFormat;
import java.util.List;

 
public class ProduksiSusuAdapter extends RecyclerView.Adapter<ProduksiSusuAdapter.MyViewHolder> {
 
    private Context context;
    private List<ProduksiSusu> produksisusuList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView produksi_susu;
 
        public MyViewHolder(View view) {
            super(view);
            produksi_susu = view.findViewById(R.id.produksi_susu_produksi_susu_data_row_list);
        }
    }
 
 
    public ProduksiSusuAdapter(Context context, List<ProduksiSusu> produksisusuList) {
        this.context = context;
        this.produksisusuList = produksisusuList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_produksi_susu, parent, false);
 
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      ProduksiSusu produksi_susu = produksisusuList.get(position);


        holder.produksi_susu.setText(nm.format(produksi_susu.getProduksiSusu()));
    
 
    }
 
    @Override
    public int getItemCount() {
        return produksisusuList.size();
    }
 
}
