package com.example.yumly.core.remote;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.IngredientModel;
import com.example.yumly.core.models.MealModel;
import java.util.ArrayList;

public interface NetworkCallback {
    void onSuccess(ArrayList<MealModel> meals);
    void onFailure(String errorMessage);
    void onSuccessRandom(ArrayList<MealModel> meals);
    void onSuccessGetCat(ArrayList<CatModel> cats);
    void onSuccessGetMealByCountry(ArrayList<MealModel> meals);
    void onSuccessGetMealByCategory(ArrayList<MealModel> meals);
    void onSuccessGetMealByIngredients(ArrayList<MealModel> meals);
    void onSuccessGetIngredients(ArrayList<IngredientModel> ingredients);
    void onSuccessGetMealDetails(ArrayList<MealModel> meals);
}
