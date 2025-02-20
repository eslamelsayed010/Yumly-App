package com.example.yumly.features.details.presenter;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.IngredientModel;
import com.example.yumly.core.remote.NetworkCallback;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.features.details.view.MyHomeView;

import java.util.ArrayList;

public class HomePresenter implements NetworkCallback {

    MyHomeView view;
    MealsRepository repo;

    public HomePresenter(MyHomeView view, MealsRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getData() {
        repo.getRemoteData(this);
    }

    public void addToFav(MealModel mealModel) {
        repo.addMeal(mealModel);
    }

    @Override
    public void onSuccess(ArrayList<MealModel> mealModel) {
        view.getData(mealModel);
    }

    @Override
    public void onFailure(String errorMessage) {
        view.onError(errorMessage);
    }

    @Override
    public void onSuccessRandom(ArrayList<MealModel> meals) {
        view.getRandomData(meals);
    }

    @Override
    public void onSuccessGetCat(ArrayList<CatModel> cats) {
    }

    @Override
    public void onSuccessGetMealByCountry(ArrayList<MealModel> meals) {

    }

    @Override
    public void onSuccessGetMealByCategory(ArrayList<MealModel> catResModels) {

    }

    @Override
    public void onSuccessGetIngredients(ArrayList<IngredientModel> ingredients) {

    }

}

