package com.example.yumly.core.remote;

import com.example.yumly.data.models.MealModel;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {

    public ArrayList<MealModel> meals;

    public static final String url = "https://www.themealdb.com/";
    private static MealRemoteDataSource mealRemoteDataSource = null;
    MealServices service;

    private MealRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MealServices.class);
    }

    public static MealRemoteDataSource getInstance(){
        if (mealRemoteDataSource == null)
            mealRemoteDataSource = new MealRemoteDataSource();
        return mealRemoteDataSource;
    }

    public void getResponse(NetworkCallback callback) {
        Call<MealResponse> response = service.getAllProducts();
        response.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    meals = response.body().getMeals();
                    callback.onSuccess(meals);
                } else {
                    callback.onFailure("NO RESPONSE");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                callback.onFailure("Failure TO GET DATA");
            }
        });
    }

    public void getRandomResponse(NetworkCallback callback) {
        Call<MealResponse> response = service.getRandomMeal();
        response.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    meals = response.body().getMeals();
                    callback.onSuccessRandom(meals);
                } else {
                    callback.onFailure("NO RESPONSE");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                callback.onFailure("Failure TO GET DATA");
            }
        });
    }
}
