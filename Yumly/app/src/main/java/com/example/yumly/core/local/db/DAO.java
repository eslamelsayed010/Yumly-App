package com.example.yumly.core.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.yumly.data.models.MealModel;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM meal_table")
    LiveData<List<MealModel>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProduct(MealModel meals);

    @Delete
    void deleteProduct(MealModel meals);
}
