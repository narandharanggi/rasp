package com.cun.rasp.view.perBB;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.R;
import com.cun.rasp.model.perBB;

import java.text.NumberFormat;
import java.util.List;

 
public class perBBAdapter extends RecyclerView.Adapter<perBBAdapter.MyViewHolder> {
 
    private Context context;
    private List<perBB> perBBList;
    NumberFormat nm = NumberFormat.getNumberInstance();
 
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView keterangan;
        public TextView tdn;
        public TextView pk;
 
        public MyViewHolder(View view) {
            super(view);
            keterangan = view.findViewById(R.id.keterangan_perbb_data_row_list);
            tdn = view.findViewById(R.id.tdn_perbb_data_row_list);     
            pk = view.findViewById(R.id.pk_perbb_data_row_list);

        }
    }
 
 
    public perBBAdapter(Context context, List<perBB> perBBList) {
        this.context = context;
        this.perBBList = perBBList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row_list_perbb, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      perBB keterangan = perBBList.get(position);
      perBB tdn = perBBList.get(position);
      perBB pk = perBBList.get(position);


        holder.keterangan.setText(keterangan.getKeterangan());
        holder.tdn.setText(nm.format(tdn.getTdn()));

        holder.pk.setText(nm.format(pk.getPk()));

 
    }
 
    @Override
    public int getItemCount() {
        return perBBList.size();
    }
 
}
