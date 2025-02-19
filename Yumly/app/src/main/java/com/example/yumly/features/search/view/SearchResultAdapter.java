package com.example.yumly.features.search.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private ArrayList<MealModel> meals;
    private static final String TAG = "MyAdapter";
    private OnItemClickListener onItemClickListener;
    String textBtn;

    public SearchResultAdapter(Context context,
                               ArrayList<MealModel> meals
    ) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_result_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        holder.titleTxt.setText(meals.get(index).getStrMeal());
        Glide.with(context).load(meals.get(index).getStrMealThumb()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setList(ArrayList<MealModel> meals){
        this.meals = meals;
    }

    public void setTextBtn(String textBtn) {
        this.textBtn = textBtn;
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTxt;
    public ImageView imageView;
    public View layout;


    public ViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        titleTxt = itemView.findViewById(R.id.meal_title_search_result_id);
        imageView = itemView.findViewById(R.id.image_result_id);
    }

}
