package com.example.yumly.features.home.view;

import com.example.yumly.data.models.MealModel;

import java.util.ArrayList;

public interface MyHomeView {
    void getData(ArrayList<MealModel> meals);
    void getRandomData(ArrayList<MealModel> meals);
    void onError(String msg);
}
