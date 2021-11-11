package com.cun.rasp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PakanAdapter extends RecyclerView.Adapter<PakanAdapter.MyViewHolder> {

    private List<Pakan> pakan;
    private PakanListener pakanListener;

    public PakanAdapter(List<Pakan> pakan, PakanListener pakanListener) {
        this.pakan = pakan;
        this.pakanListener = pakanListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pakan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindPakan(pakan.get(position));
    }

    @Override
    public int getItemCount() {
        return pakan.size();
    }

    public List<Pakan> getSelectedPakan(){
        List<Pakan> selectedPakan = new ArrayList<>();
        for(Pakan pak: pakan){
            selectedPakan.add(pak);
        }

        return selectedPakan;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutPakan;
        private View viewBackground;
        private TextView textView;
        private ImageView imageSelected;

        private MyViewHolder(View itemView){
            super(itemView);
            layoutPakan = itemView.findViewById(R.id.layoutTvshow);
            textView = itemView.findViewById(R.id.textName);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        void bindPakan(final Pakan pakan){
            textView.setText(pakan.name);

            if(pakan.isSelected){
                viewBackground.setBackgroundResource(R.drawable.pakan_selected_bg);
                imageSelected.setVisibility(View.VISIBLE);
            } else{
                viewBackground.setBackgroundResource(R.drawable.pakan_bg);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pakan.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.pakan_bg);
                        imageSelected.setVisibility(View.GONE);
                        pakan.isSelected = false;
                        if(getSelectedPakan().size()==0){
                            pakanListener.onPakan(false);
                        }
                    } else{
                        viewBackground.setBackgroundResource(R.drawable.pakan_selected_bg);
                        imageSelected.setVisibility(View.VISIBLE);
                        pakan.isSelected = true;
                        pakanListener.onPakan(true);
                    }

                }
            });
        }
    }

}
