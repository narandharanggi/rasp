package com.cun.rasp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.model.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MultiViewHolder> {
    private Context context;
    private ArrayList<Food> foods;

    public FoodAdapter(Context context, ArrayList<Food> foods){
        this.context = context;
        this.foods = foods;
    }

    public void setFoods(ArrayList<Food> foods){
        this.foods = new ArrayList<>();
        this.foods = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new MultiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiViewHolder holder, int position) {
        holder.bind(foods.get(position));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class MultiViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bind(final Food food){
            imageView.setVisibility(food.isChecked() ? View.VISIBLE : View.GONE);
            textView.setText(food.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    food.setChecked(!food.isChecked());
                    imageView.setVisibility(food.isChecked() ? View.VISIBLE : View.GONE);
                }
            });
        }

        public ArrayList<Food> getAll(){
            return foods;
        }


    }

    public ArrayList<Food> getSelected() {
        ArrayList<Food> selected = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).isChecked()) {
                selected.add(foods.get(i));
            }
        }
        return selected;
    }
}
