package com.cun.rasp.view;

import android.content.Context;

import android.provider.ContactsContract;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.BahanPakan;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

 
public class BahanPakanAdapter extends RecyclerView.Adapter<BahanPakanAdapter.MyViewHolder> {
 
    private Context context;
    private List<BahanPakan> bahanPakanList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // public TextView note;
        // public TextView dot;
        // public TextView timestamp;
        public TextView nama_pakan;
        public TextView tdn;
        public TextView bk;
        public TextView pk;
        public TextView ca;
        public TextView p;
        public TextView harga;
 
        public MyViewHolder(View view) {
            super(view);
            nama_pakan = view.findViewById(R.id.nama_pakan_BahanPakan);
            tdn = view.findViewById(R.id.tdn_BahanPakan);
            bk = view.findViewById(R.id.bk_BahanPakan);
            pk = view.findViewById(R.id.pk_BahanPakan);
            ca = view.findViewById(R.id.ca_BahanPakan);
            p = view.findViewById(R.id.p_BahanPakan);
            harga = view.findViewById(R.id.harga_BahanPakan);
        }
    }
 
 
    public BahanPakanAdapter(Context context, List<BahanPakan> bahanPakanList) {
        this.context = context;
        this.bahanPakanList = bahanPakanList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_bahan_pakan, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      BahanPakan nama_pakan = bahanPakanList.get(position);
      BahanPakan tdn = bahanPakanList.get(position);
      BahanPakan bk = bahanPakanList.get(position);
      BahanPakan pk = bahanPakanList.get(position);
      BahanPakan ca = bahanPakanList.get(position);
      BahanPakan p = bahanPakanList.get(position);
      BahanPakan harga = bahanPakanList.get(position);

        holder.nama_pakan.setText(nama_pakan.getNamaPakan());
        holder.tdn.setText(nm.format(tdn.getTdn()));
        holder.bk.setText(nm.format(bk.getBk()));
        holder.pk.setText(nm.format(pk.getPk()));
        holder.ca.setText(nm.format(ca.getCa()));
        holder.p.setText(nm.format(p.getP()));
        holder.harga.setText(String.valueOf(harga.getHarga()));
 
    }
 
    @Override
    public int getItemCount() {
        return bahanPakanList.size();
    }
 
}
