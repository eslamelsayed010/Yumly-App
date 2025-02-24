package com.example.yumly.features.details.presenter;

import android.annotation.SuppressLint;

import com.example.yumly.core.firebase.FireBaseRepo;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.features.details.view.DetailsView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {

    DetailsView view;
    MealsRepository repo;
    FireBaseRepo fireBaseRepo;

    public DetailsPresenter(DetailsView view, MealsRepository repo) {
        this.view = view;
        this.repo = repo;
        fireBaseRepo = FireBaseRepo.getInstance();
    }

    @SuppressLint("CheckResult")
    public void addToFav(MealModel mealModel, String id) {
        repo.addMealToFav(mealModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessAddToFav(mealModel),
                        error -> view.onError(error.getMessage())
                );
        fireBaseRepo.insertFav(mealModel, "favorite", id);
    }

    @SuppressLint("CheckResult")
    public void removeFromFav(MealModel mealModel, String id) {
        repo.deleteMealFromFav(mealModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessRemoveFromFav(mealModel),
                        error -> view.onError(error.getMessage())
                );

        fireBaseRepo.deleteFromFireBase(id, mealModel, "", "favorite");
    }

    @SuppressLint("CheckResult")
    public void getMealFromDatabase() {
        repo.getFavMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> view.onSuccessGetMealFromDB(new ArrayList<>(meal)),
                        error -> view.onError(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void insertToPlane(PlanModel planModel) {
        repo.insertToPlane(planModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessAddToPlan(planModel),
                        error -> view.onError(error.getMessage())
                );

        fireBaseRepo.insert(planModel, "plan");
    }
}

