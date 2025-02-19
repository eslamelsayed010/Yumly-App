package com.example.yumly.core.remote;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.MealModel;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MealResponse {

    @SerializedName("meals")
    private ArrayList<MealModel> meals;

    @SerializedName("categories")
    private ArrayList<CatModel> cats;

    public MealResponse(ArrayList<MealModel> meals, ArrayList<CatModel> cats) {
        this.meals = meals;
        this.cats = cats;
    }

    public ArrayList<MealModel> getMeals() {
        return meals;
    }

    public void setProducts(ArrayList<MealModel> meals) {
        this.meals = meals;
    }

    public void setMeals(ArrayList<MealModel> meals) {
        this.meals = meals;
    }

    public ArrayList<CatModel> getCats() {
        return cats;
    }

    public void setCats(ArrayList<CatModel> cats) {
        this.cats = cats;
    }
}
