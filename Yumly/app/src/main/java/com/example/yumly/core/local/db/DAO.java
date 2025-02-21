package com.example.yumly.core.local.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.yumly.core.models.MealModel;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DAO {

    @Query("SELECT * FROM meal_table")
    Flowable<List<MealModel>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addProduct(MealModel meals);

    @Delete
    Completable deleteProduct(MealModel meals);
}
