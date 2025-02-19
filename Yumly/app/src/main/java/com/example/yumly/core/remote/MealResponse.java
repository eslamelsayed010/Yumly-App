package com.example.yumly.core.remote;

import com.example.yumly.core.models.MealModel;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MealResponse {

    @SerializedName("meals")
    private ArrayList<MealModel> meals;

    public MealResponse(ArrayList<MealModel> meals) {
        this.meals = meals;
    }

    public ArrayList<MealModel> getMeals() {
        return meals;
    }

    public void setProducts(ArrayList<MealModel> meals) {
        this.meals = meals;
    }
}
