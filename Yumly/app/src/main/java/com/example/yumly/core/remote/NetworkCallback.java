package com.example.yumly.core.remote;

import com.example.yumly.data.models.MealModel;
import java.util.ArrayList;

public interface NetworkCallback {
    void onSuccess(ArrayList<MealModel> meals);
    void onFailure(String errorMessage);
    void onSuccessRandom(ArrayList<MealModel> meals);
}
