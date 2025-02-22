package com.example.yumly.features.plan.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private ArrayList<PlanModel> planModels;
    private static final String TAG = "MyAdapter";
    private OnPlanClickListener onPlanClickListener;

    public MyAdapter(Context context,
                     ArrayList<PlanModel> planModels,
                     OnPlanClickListener onPlanClickListener
    ) {
        this.context = context;
        this.planModels = planModels;
        this.onPlanClickListener = onPlanClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plane_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        MealModel mealModel = planModels.get(index).getMeal();
        holder.titleTxt.setText(mealModel.getStrMeal());
        holder.dayTxt.setText("At: " + planModels.get(index).getDay());
        holder.descTxt.setText(mealModel.getStrInstructions());
        holder.country.setText(mealModel.getStrArea());
        Glide.with(context).load(mealModel.getStrMealThumb()).into(holder.imageView);
        holder.deleteBtn.setOnClickListener(v-> onPlanClickListener.onClick(planModels.get(index)));
        holder.cardView.setOnClickListener(v-> onPlanClickListener.onCardClick(mealModel));
    }

    @Override
    public int getItemCount() {
        return planModels.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView dayTxt;
    public TextView titleTxt;
    public TextView descTxt;
    public TextView country;
    public ImageView imageView;
    public CardView cardView;
    public ImageView deleteBtn;
    public View layout;


    public ViewHolder(View itemView) {
        super(itemView);
        layout = itemView;
        dayTxt = itemView.findViewById(R.id.day_txt_id);
        titleTxt = itemView.findViewById(R.id.title_plan_id);
        descTxt = itemView.findViewById(R.id.desc_plan_id);
        country = itemView.findViewById(R.id.country_plan_id);
        imageView = itemView.findViewById(R.id.plan_image_id);
        cardView = itemView.findViewById(R.id.plan_item_id);
        deleteBtn = itemView.findViewById(R.id.delete_btn_item_id);
    }

}
