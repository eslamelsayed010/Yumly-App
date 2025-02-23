package com.example.yumly.core.local.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DAO {

    @Query("SELECT * FROM meal_table")
    Flowable<List<MealModel>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addProduct(MealModel meals);

    @Delete
    Completable deleteProduct(MealModel meals);

    @Query("SELECT * FROM plan_table ORDER BY substr(day, 7, 4) || substr(day, 4, 2) || substr(day, 1, 2) ASC")
    Flowable<List<PlanModel>> getAllPlanByDate();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertToPlane(PlanModel planModel);

    @Query("DELETE FROM plan_table WHERE userID = :userID AND meal = :meal AND day = :day")
    Completable deleteFromPlan(String userID, MealModel meal, String day);
}
