package com.example.yumly.features.details.view;

import com.example.yumly.core.models.MealModel;

import java.util.ArrayList;

public interface MyHomeView {
    void getData(ArrayList<MealModel> meals);
    void getRandomData(ArrayList<MealModel> meals);
    void onError(String msg);
}
