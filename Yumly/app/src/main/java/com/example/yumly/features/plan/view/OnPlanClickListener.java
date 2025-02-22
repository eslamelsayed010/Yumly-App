package com.example.yumly.features.plan.view;

import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

public interface OnPlanClickListener {
    void onClick(PlanModel planModel);
    void onCardClick(MealModel mealModel);
}
