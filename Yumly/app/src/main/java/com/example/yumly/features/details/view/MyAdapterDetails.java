package com.example.yumly.features.details.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.IngredientDetailsModel;
import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public class MyAdapterDetails extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final ArrayList<IngredientDetailsModel> ingredientDetails;
    private static final String TAG = "MyAdapter";

    public MyAdapterDetails(Context context, ArrayList<IngredientDetailsModel> ingredientDetails) {
        this.context = context;
        this.ingredientDetails = ingredientDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_item_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        IngredientDetailsModel ingredientDetailsModel = ingredientDetails.get(index);
        holder.title.setText(ingredientDetailsModel.getIngredient());
        holder.measure.setText(ingredientDetailsModel.getMeasure());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredientDetailsModel.getIngredient()+".png").into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ingredientDetails.size();
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title;
    TextView measure;
    public View layout;


    public ViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        imageView = itemView.findViewById(R.id.image_ingredients_item_details_id);
        title = itemView.findViewById(R.id.title_ingredients_item_details_id);
        measure = itemView.findViewById(R.id.measure_ingredients_item_details_id);
    }

}
