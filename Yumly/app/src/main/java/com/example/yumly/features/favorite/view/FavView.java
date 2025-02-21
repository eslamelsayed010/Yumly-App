package com.example.yumly.features.favorite.view;

import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;
public interface FavView {
    void getMeals(ArrayList<MealModel> models);
    void getError(String error);
    void onSuccessRemoveFromFav(MealModel mealModel);
}
