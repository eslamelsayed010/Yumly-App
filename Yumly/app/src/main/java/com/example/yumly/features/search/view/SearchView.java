package com.example.yumly.features.search.view;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.CountryModel;
import com.example.yumly.core.models.IngredientModel;
import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public interface SearchView {
    void getData(ArrayList<CountryModel> countries);
    void getDataByCountry(ArrayList<MealModel> countries);
    void getDataByCategory(ArrayList<MealModel> meals);
    void getDataByIngredients(ArrayList<MealModel> meals);
    void getIngredients(ArrayList<IngredientModel> ingredients);
    void getCategory(ArrayList<CatModel> cats);
    void onError(String msg);
}
