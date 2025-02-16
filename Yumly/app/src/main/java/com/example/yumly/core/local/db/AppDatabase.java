package com.example.yumly.core.local.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.yumly.data.models.MealModel;

@Database(entities = {MealModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDataBase = null;
    public abstract DAO getDAO();

    public static AppDatabase getInstance(Context context){
        if (appDataBase == null)
            appDataBase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "mealDb"
            ).build();
        return appDataBase;
    }
}
