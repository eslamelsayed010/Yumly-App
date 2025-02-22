package com.example.yumly.features.plan.view;

import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

import java.util.ArrayList;
public interface PlanView {
    void getMeals(ArrayList<PlanModel> models);
    void getError(String error);
    void onSuccessRemoveFromPlan(MealModel mealModel);
}
