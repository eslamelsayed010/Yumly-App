package com.example.yumly.features.search.view;

import com.example.yumly.data.models.CountryModel;
import com.example.yumly.data.models.MealModel;

import java.util.ArrayList;

public interface SearchView {
    void getData(ArrayList<CountryModel> countries);
    void onError(String msg);
}
