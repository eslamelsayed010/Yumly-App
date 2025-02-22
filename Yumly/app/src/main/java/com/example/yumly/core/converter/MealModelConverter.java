package com.example.yumly.core.converter;

import androidx.room.TypeConverter;
import com.example.yumly.core.models.MealModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class MealModelConverter {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromMealModel(MealModel meal) {
        return gson.toJson(meal); // Convert object to JSON string
    }

    @TypeConverter
    public static MealModel toMealModel(String mealJson) {
        Type type = new TypeToken<MealModel>() {}.getType();
        return gson.fromJson(mealJson, type); // Convert JSON string to object
    }
}

