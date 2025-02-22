package com.example.yumly.core.local;

import android.content.Context;

import com.example.yumly.core.local.db.AppDatabase;
import com.example.yumly.core.local.db.DAO;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

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
        return dao.getAllPlanByDay();
    }

    public Completable insertToPlane(PlanModel planModel) {
        return dao.insertToPlane(planModel);
    }

    public Completable deleteFromPlan(String userID, MealModel meal, String day) {
        return dao.deleteFromPlan(userID, meal, day);

    }
}
