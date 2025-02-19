package com.example.yumly.features.search.view;

import com.example.yumly.core.models.CountryModel;
import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public interface SearchView {
    void getData(ArrayList<CountryModel> countries);
    void getDataByCountry(ArrayList<MealModel> countries);
    void getCategory(ArrayList<String> strCategory);
    void onError(String msg);
    void onClick(MealModel mealModel);
}
