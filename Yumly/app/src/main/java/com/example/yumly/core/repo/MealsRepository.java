package com.example.yumly.core.repo;

import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.NetworkCallback;

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


    public void addMeal(MealModel productModel){
        localDataSource.insert(productModel);
    }

    public void deleteMeal(MealModel productModel){
        localDataSource.delete(productModel);
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
