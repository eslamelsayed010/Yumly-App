package com.example.yumly.core.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealServices {

    @GET("/api/json/v1/1/search.php?s=")
    Call<MealResponse> getAllProducts();

    @GET("/api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();
}
