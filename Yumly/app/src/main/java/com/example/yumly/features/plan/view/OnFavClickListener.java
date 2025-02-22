package com.example.yumly.features.plan.view;

import com.example.yumly.core.models.MealModel;

public interface OnFavClickListener {
    void onClick(MealModel mealModel);
    void onCardClick(MealModel mealModel);
}
