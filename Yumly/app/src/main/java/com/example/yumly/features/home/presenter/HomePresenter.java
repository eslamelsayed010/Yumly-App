package com.example.yumly.features.home.presenter;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.IngredientModel;
import com.example.yumly.core.remote.NetworkCallback;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.features.home.view.MyHomeView;
import java.util.ArrayList;

public class HomePresenter implements NetworkCallback {

    MyHomeView view;
    MealsRepository repo;

    public HomePresenter(MyHomeView view, MealsRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void getAllMeals(){
        repo.getAllMeals(this);
    }

    public void getRandomMeal(){
        repo.getRandomMeal(this);
    }

    public void getMealDetails(String id){
        repo.getMealDetails(this, id);
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
    public void onSuccessGetMealByIngredients(ArrayList<MealModel> meals) {

    }

    @Override
    public void onSuccessGetIngredients(ArrayList<IngredientModel> ingredients) {

    }

    @Override
    public void onSuccessGetMealDetails(ArrayList<MealModel> models) {
        view.getMealDetails(models);
    }
}

