package com.example.yumly.features.plan.presenter;

import android.annotation.SuppressLint;

import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.features.plan.view.FavView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenter {

    FavView view;
    MealsRepository repo;


    public FavPresenter(FavView view, MealsRepository repo) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void getMeals(){
        repo.getFavMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealModels -> view.getMeals(new ArrayList<>(mealModels)),
                        error -> view.getError(error.getMessage())
                );

    }

    @SuppressLint("CheckResult")
    public void removeProduct(MealModel mealModel){
        repo.deleteMealFromFav(mealModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessRemoveFromFav(mealModel),
                        error -> view.getError(error.getMessage())
                );
    }
}
