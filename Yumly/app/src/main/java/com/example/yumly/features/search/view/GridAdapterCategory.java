package com.example.yumly.features.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumly.R;

import java.util.ArrayList;

public class GridAdapterCategory extends RecyclerView.Adapter<GridAdapterCategory.ViewHolder> {

    private Context context;
    private ArrayList<String> strCategory;

    public GridAdapterCategory(Context context, ArrayList<String> strCategory) {
        this.context = context;
        this.strCategory = strCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = strCategory.get(position);
        holder.textView.setText(category);
        holder.imageView.setImageResource(R.drawable.food_image);
    }

    @Override
    public int getItemCount() {
        return strCategory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ships_image_id);
            textView = itemView.findViewById(R.id.ships_text_id);
        }
    }
}