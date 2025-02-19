package com.example.yumly.features.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.CatModel;

import java.util.ArrayList;

public class GridAdapterCategory extends RecyclerView.Adapter<GridAdapterCategory.ViewHolder> {

    private Context context;
    private ArrayList<CatModel> cats;

    public GridAdapterCategory(Context context, ArrayList<CatModel> cats) {
        this.context = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatModel category = cats.get(position);
        holder.textView.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cats.size();
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