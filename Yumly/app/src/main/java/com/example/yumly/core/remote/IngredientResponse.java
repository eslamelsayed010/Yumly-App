package com.example.yumly.core.remote;

import com.example.yumly.core.models.IngredientModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IngredientResponse {
    @SerializedName("meals")
    private ArrayList<IngredientModel> ingredients;

    public IngredientResponse(ArrayList<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }
}
