package com.example.yumly.core.local;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.yumly.core.local.db.AppDatabase;
import com.example.yumly.core.local.db.DAO;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDataSource {

    private Context context;
    private DAO dao;
    private Flowable<List<MealModel>> data;
    private static MealsLocalDataSource mealsLocalDataSource = null;

    private MealsLocalDataSource(Context context) {
        this.context = context;
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        dao = database.getDAO();
        data = dao.getAllProduct();
    }

    public static MealsLocalDataSource getInstance(Context context) {
        if (mealsLocalDataSource == null)
            mealsLocalDataSource = new MealsLocalDataSource(context);
        return mealsLocalDataSource;
    }

    public Flowable<List<MealModel>> getAllData() {
        return data;
    }

    public Completable delete(MealModel mealModel) {
        return dao.deleteProduct(mealModel);
    }

    public Completable insert(MealModel mealModel) {
        return dao.addProduct(mealModel);
    }

    public Flowable<List<PlanModel>> getAllPlanByDay() {
        return dao.getAllPlanByDate();
    }

    public Completable insertToPlane(PlanModel planModel) {
        return dao.insertToPlane(planModel);
    }

    public Completable deleteFromPlan(String userID, MealModel meal, String day) {
        return dao.deleteFromPlan(userID, meal, day);
    }

    public Completable insertAll(List<PlanModel> planModels) {
        return dao.insertAll(planModels);
    }

    public Completable insertAllFav(List<MealModel> mealModels) {
        return dao.insertAllFav(mealModels);
    }

    @SuppressLint("CheckResult")
    public void deleteAll() {
        dao.deleteAllFav().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();

        dao.deleteAllPlan().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
