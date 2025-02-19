package com.example.yumly.core.remote;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealServices {

    @GET("/api/json/v1/1/search.php?s=")
    Call<MealResponse> getAllProducts();

    @GET("/api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();

    @GET("/api/json/v1/1/filter.php")
    Single<MealResponse> getMealByCountry(@Query("a") String country);

    @GET("/api/json/v1/1/categories.php")
    Single<MealResponse> getCategory();
}
