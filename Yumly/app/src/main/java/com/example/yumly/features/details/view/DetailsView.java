package com.example.yumly.features.details.view;

import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public interface DetailsView {
    void onSuccessAddToFav(MealModel mealModel);
    void onSuccessRemoveFromFav(MealModel mealModel);
    void onSuccessGetMealFromDB(ArrayList<MealModel> meals);
    void onError(String msg);
}
