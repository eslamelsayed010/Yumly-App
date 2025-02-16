package com.example.yumly.core.repo;

import androidx.lifecycle.LiveData;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.data.models.MealModel;
import java.util.List;

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

    public LiveData<List<MealModel>> getLocalProduct(){
        return localDataSource.getAllData();
    }


    public void addMeal(MealModel productModel){
        localDataSource.insert(productModel);
    }

    public void deleteMeal(MealModel productModel){
        localDataSource.delete(productModel);
    }


    public void getRemoteData(com.example.yumly.core.remote.NetworkCallback networkCallback){
        remoteDataSource.getResponse(networkCallback);
        remoteDataSource.getRandomResponse(networkCallback);
    }
}
