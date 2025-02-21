package com.example.yumly.features.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.MealModel;
import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MealModel> meals;
    OnItemClickListenerHome listener;

    public GridAdapter(Context context,
                       ArrayList<MealModel> meals,
                       OnItemClickListenerHome listener
                       ) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealModel meal = meals.get(position);
        holder.textView.setText(meal.getStrMeal());
        holder.textView2.setText(meal.getStrInstructions());
        holder.textView3.setText(meal.getStrCategory());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(v -> listener.onClick(meal));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.grid_image_id);
            textView = itemView.findViewById(R.id.grid_txt_id);
            textView2 = itemView.findViewById(R.id.grid_desc_id);
            textView3 = itemView.findViewById(R.id.grid_tag_id);
            linearLayout = itemView.findViewById(R.id.home_grid_item);
        }
    }
}