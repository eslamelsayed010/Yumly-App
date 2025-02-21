package com.example.yumly.core.remote;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealServices {

    @GET("/api/json/v1/1/search.php?s=")
    Single<MealResponse> getAllProducts();

    @GET("/api/json/v1/1/random.php")
    Single<MealResponse> getRandomMeal();

    @GET("/api/json/v1/1/filter.php")
    Single<MealResponse> getMealByCountry(@Query("a") String country);

    @GET("/api/json/v1/1/filter.php")
    Single<MealResponse> getMealByCategory(@Query("c") String cat);

    @GET("/api/json/v1/1/filter.php")
    Single<MealResponse> getMealByIngredients(@Query("i") String cat);

    @GET("/api/json/v1/1/lookup.php")
    Single<MealResponse> getMealDetails(@Query("i") String id);

    @GET("/api/json/v1/1/categories.php")
    Single<MealResponse> getCategory();

    @GET("/api/json/v1/1/list.php?i=list")
    Single<IngredientResponse> getIngredients();
}
