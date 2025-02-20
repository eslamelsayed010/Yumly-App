package com.example.yumly.features.search.view;

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
import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.IngredientModel;

import java.util.ArrayList;

public class GridAdapterIngredients extends RecyclerView.Adapter<GridAdapterIngredients.ViewHolder> {

    private Context context;
    private ArrayList<IngredientModel> ingredients;
    OnItemClickListener listener;

    public GridAdapterIngredients(Context context, ArrayList<IngredientModel> ingredients, OnItemClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientModel ingredientModel = ingredients.get(position);
        holder.textView.setText(ingredientModel.getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredientModel.getStrIngredient()+".png").into(holder.imageView);
        holder.linearLayout.setOnClickListener(v-> listener.onIngredientsClick(ingredientModel));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ships_image_id);
            textView = itemView.findViewById(R.id.ships_text_id);
            linearLayout =itemView.findViewById(R.id.item_click_id);
        }
    }
}