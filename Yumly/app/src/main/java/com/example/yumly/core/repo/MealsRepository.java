package com.example.yumly.core.repo;

import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.models.PlanModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.NetworkCallback;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsRepository {
    MealsLocalDataSource localDataSource;
    MealRemoteDataSource remoteDataSource;
    private static MealsRepository mealsRepository = null;

    private MealsRepository(MealsLocalDataSource localDataSource,
                            MealRemoteDataSource remoteDataSource
    ) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static MealsRepository getInstance(MealsLocalDataSource localDataSource,
                                              MealRemoteDataSource remoteDataSource
    ){
        if (mealsRepository == null)
            mealsRepository = new MealsRepository(localDataSource, remoteDataSource);
        return mealsRepository;
    }


    public Flowable<List<MealModel>> getFavMeal(){
        return localDataSource.getAllData();
    }

    public Completable addMealToFav(MealModel mealModel){
        return localDataSource.insert(mealModel);
    }

    public Completable deleteMealFromFav(MealModel mealModel){
        return localDataSource.delete(mealModel);
    }

    public Flowable<List<PlanModel>> getAllPlanByDay(){
        return localDataSource.getAllPlanByDay();
    }

    public Completable insertToPlane(PlanModel planModel){
        return localDataSource.insertToPlane(planModel);
    }

    public Completable deleteFromPlan(String userID, MealModel meal, String day){
        return localDataSource.deleteFromPlan(userID, meal, day);
    }


    public void getAllMeals(NetworkCallback networkCallback){
        remoteDataSource.getResponse(networkCallback);
    }

    public void getRandomMeal(NetworkCallback networkCallback){
        remoteDataSource.getRandomResponse(networkCallback);
    }

    public void getMealDetails(NetworkCallback networkCallback, String id){
        remoteDataSource.getMealDetails(networkCallback, id);
    }

}
