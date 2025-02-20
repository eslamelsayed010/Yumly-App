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
    public static final String url = "https://www.themealdb.com/";
    private static MealRemoteDataSource mealRemoteDataSource = null;
    MealServices service;

    private MealRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(MealServices.class);
    }

    public static MealRemoteDataSource getInstance() {
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

    @SuppressLint("CheckResult")
    public void getMealByCountry(NetworkCallback callback, String country) {
        service.getMealByCountry(country)
                .subscribeOn(Schedulers.io())
                .map(response -> response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessGetMealByCountry(meals),
                        error -> callback.onFailure(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getMealByCategory(NetworkCallback callback, String cat) {
        service.getMealByCategory(cat)
                .subscribeOn(Schedulers.io())
                .map(response -> response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessGetMealByCategory(meals),
                        error -> callback.onFailure(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getMealByIngredients(NetworkCallback callback, String ingredient) {
        service.getMealByIngredients(ingredient)
                .subscribeOn(Schedulers.io())
                .map(response -> response.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> callback.onSuccessGetMealByCategory(meals),
                        error -> callback.onFailure(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getCategory(NetworkCallback callback) {
        service.getCategory()
                .subscribeOn(Schedulers.io())
                .map(response -> response.getCats())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cat -> callback.onSuccessGetCat(cat),
                        error -> callback.onFailure(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getIngredients(NetworkCallback callback) {
        service.getIngredients()
                .subscribeOn(Schedulers.io())
                .map(response -> response.getIngredients())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredients -> callback.onSuccessGetIngredients(ingredients),
                        error -> callback.onFailure(error.getMessage())
                );
    }
}
