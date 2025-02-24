package com.example.yumly.features.favorite.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.yumly.core.firebase.FireBaseRepo;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.features.favorite.view.FavView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenter {

    FavView view;
    MealsRepository repo;
    FireBaseRepo fireBaseRepo;
    List<MealModel> mealModels = new ArrayList<>();


    public FavPresenter(FavView view, MealsRepository repo) {
        this.repo = repo;
        this.view = view;
        fireBaseRepo = FireBaseRepo.getInstance();
    }

    @SuppressLint("CheckResult")
    public void getMeals() {
        repo.getFavMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealModels -> view.getMeals(new ArrayList<>(mealModels)),
                        error -> view.getError(error.getMessage())
                );

    }

    public void insertAllFavoriteFromFirebase(String userID, String type) {
        FireBaseRepo.getInstance().getFavorite(userID, type)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            return;
                        }
                        for (DataSnapshot daySnapshot : snapshot.getChildren()) {
                            MealModel meal = daySnapshot.getValue(MealModel.class);
                            if (meal != null)
                                mealModels.add(meal);
                            else
                                Log.e("Firebase", "Meal is null");
                        }
                        repo.insertAllFav(mealModels)
                                .subscribeOn(Schedulers.io())
                                .subscribe(
                                () -> Log.d("Database", "meals inserted successfully"),
                                error -> Log.e("Database", "Error inserting meals: " + error.getMessage())
                        );
                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                        Log.e("TAG", "onCancelled: Firebase request canceled - " + error.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void removeProduct(MealModel mealModel, String id) {
        repo.deleteMealFromFav(mealModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.onSuccessRemoveFromFav(mealModel),
                        error -> view.getError(error.getMessage())
                );
        fireBaseRepo.deleteFromFireBase(id, mealModel, "", "favorite");
    }
}
