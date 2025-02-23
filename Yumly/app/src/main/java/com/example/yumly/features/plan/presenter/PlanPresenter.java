package com.example.yumly.features.plan.presenter;

import android.annotation.SuppressLint;

import com.example.yumly.core.models.PlanModel;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.features.plan.view.PlanView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter {

    PlanView view;
    MealsRepository repo;


    public PlanPresenter(PlanView view, MealsRepository repo) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void getMeals(){
        repo.getAllPlanByDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planModels -> view.getMeals(new ArrayList<>(planModels)),
                        error -> view.getError(error.getMessage())
                );

    }

    @SuppressLint("CheckResult")
    public void removePlane(PlanModel model){
        repo.deleteFromPlan(model.getUserID(), model.getMeal(), model.getDay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessRemoveFromPlan(model.getMeal()),
                        error -> view.getError(error.getMessage())
                );
    }
}
