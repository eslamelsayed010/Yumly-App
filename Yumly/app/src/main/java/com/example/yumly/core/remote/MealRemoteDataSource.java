package com.example.yumly.core.remote;

import android.annotation.SuppressLint;

import com.example.yumly.core.models.MealModel;
import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {

    public ArrayList<MealModel> meals;
    public ArrayList<String> strCategories;

    public static final String url = "https://www.themealdb.com/";
    private static MealRemoteDataSource mealRemoteDataSource = null;
    MealServices service;

    private MealRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

    public void getStrCategoryResponse(NetworkCallback callback) {
        Call<CatResponse> response = service.getStrCategory();
        response.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                if (response.isSuccessful()) {
                    strCategories = response.body().getCat();
                    callback.onSuccessGetCat(strCategories);
                } else {
                    callback.onFailure("NO RESPONSE");
                }
            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable throwable) {
                callback.onFailure("Failure TO GET DATA"+ throwable.toString());
            }
        });
    }

    @SuppressLint("CheckResult")
    public void getMealByCountry(NetworkCallback callback, String country) {
        service.getMealByCountry(country)
                .subscribeOn(Schedulers.io())
                .map(response -> response.getMeals()) // Extract the list of products
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessGetMealByCountry(meals), // Pass the list of products
                        error -> callback.onFailure(error.getMessage()) // Handle errors
                );
    }
}
