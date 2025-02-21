package com.example.yumly.features.details.presenter;

import android.annotation.SuppressLint;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.features.details.view.DetailsView;
import java.util.ArrayList;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {

    DetailsView view;
    MealsRepository repo;

    public DetailsPresenter(DetailsView view, MealsRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    @SuppressLint("CheckResult")
    public void addToFav(MealModel mealModel){
        repo.addMealToFav(mealModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessAddToFav(mealModel),
                        error -> view.onError(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void removeFromFav(MealModel mealModel){
        repo.deleteMealFromFav(mealModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessRemoveFromFav(mealModel),
                        error -> view.onError(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getMealFromDatabase(){
        repo.getFavMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> view.onSuccessGetMealFromDB(new ArrayList<>(meal)),
                        error -> view.onError(error.getMessage())
                );
    }
}

