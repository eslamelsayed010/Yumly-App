package com.example.yumly.core.local.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.yumly.core.converter.MealModelConverter;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;

@Database(entities = {MealModel.class, PlanModel.class}, version = 4)
@TypeConverters(MealModelConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDataBase = null;
    public abstract DAO getDAO();

    public static AppDatabase getInstance(Context context){
        if (appDataBase == null)
            appDataBase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "mealDatabase2"
            ).build();
        return appDataBase;
    }
}
