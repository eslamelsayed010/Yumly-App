package com.example.yumly.features.plan.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.yumly.core.firebase.FireBaseRepo;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.features.plan.view.PlanView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter {

    PlanView view;
    MealsRepository repo;
    FireBaseRepo fireBaseRepo;
    List<PlanModel> planModels = new ArrayList<>();


    public PlanPresenter(PlanView view, MealsRepository repo) {
        this.repo = repo;
        this.view = view;
        fireBaseRepo = FireBaseRepo.getInstance();
    }

    @SuppressLint("CheckResult")
    public void getMeals() {
        repo.getAllPlanByDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planModels -> view.getMeals(new ArrayList<>(planModels)),
                        error -> view.getError(error.getMessage())
                );

    }

    @SuppressLint("CheckResult")
    public void removePlane(PlanModel model) {
        repo.deleteFromPlan(model.getUserID(), model.getMeal(), model.getDay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessRemoveFromPlan(model.getMeal()),
                        error -> view.getError(error.getMessage())
                );
        fireBaseRepo.deleteFromFireBase(model.getUserID(), model.getMeal(), model.getDay(), "plan");
    }

    public void insertAllPlansFromFirebase(String userID, String type) {
        FireBaseRepo.getInstance().getAllPlansForUser(userID, type)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            return;
                        }
                        for (DataSnapshot daySnapshot : snapshot.getChildren()) {
                            String dateDay = daySnapshot.getKey();
                            PlanModel planModel = daySnapshot.getValue(PlanModel.class);
                            if (planModel != null)
                                planModels.add(planModel);
                            else
                                Log.e("Firebase", "Meal is null for date: " + dateDay);
                        }
                        repo.insertAll(planModels)
                                .subscribeOn(Schedulers.io())
                                .subscribe(
                                        () -> Log.d("Database", "plan inserted successfully"),
                                        error -> Log.e("Database", "Error inserting plan: " + error.getMessage())
                                );
                    }
                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                        Log.e("TAG", "onCancelled: Firebase request canceled - " + error.getMessage());
                    }
                });


    }
}
