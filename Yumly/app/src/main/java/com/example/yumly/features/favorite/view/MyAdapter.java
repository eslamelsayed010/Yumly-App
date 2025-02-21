package com.example.yumly.features.favorite.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private ArrayList<MealModel> meals;
    private static final String TAG = "MyAdapter";
    private OnFavClickListener onFavClickListener;
    String textBtn;

    public MyAdapter(Context context,
                     ArrayList<MealModel> meals,
                     OnFavClickListener onFavClickListener
    ) {
        this.context = context;
        this.meals = meals;
        this.onFavClickListener = onFavClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fav_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        MealModel mealModel = meals.get(index);
        holder.titleTxt.setText(mealModel.getStrMeal());
        holder.descTxt.setText(mealModel.getStrInstructions());
        holder.country.setText(mealModel.getStrArea());
        Glide.with(context).load(mealModel.getStrMealThumb()).into(holder.imageView);
        holder.favBtn.setOnClickListener(v-> onFavClickListener.onClick(mealModel));
        holder.cardView.setOnClickListener(v-> onFavClickListener.onCardClick(mealModel));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setList(ArrayList<MealModel> meals){
        this.meals = meals;
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTxt;
    public TextView descTxt;
    public TextView country;
    public ImageView imageView;
    public CardView cardView;
    public ImageView favBtn;
    public View layout;


    public ViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        titleTxt = itemView.findViewById(R.id.title_fav_id);
        descTxt = itemView.findViewById(R.id.desc_fav_id);
        country = itemView.findViewById(R.id.country_fav_id);
        imageView = itemView.findViewById(R.id.fav_image_id);
        cardView = itemView.findViewById(R.id.fav_item_id);
        favBtn = itemView.findViewById(R.id.fav_btn_item_id);
    }

}
