package com.example.yumly.features.details.view;

import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

import java.util.ArrayList;

public interface DetailsView {
    void onSuccessAddToFav(MealModel mealModel);
    void onSuccessRemoveFromFav(MealModel mealModel);
    void onSuccessGetMealFromDB(ArrayList<MealModel> meals);
    void onSuccessAddToPlan(PlanModel planModel);
    void onError(String msg);
}
