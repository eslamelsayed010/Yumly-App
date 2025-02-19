package com.example.yumly.core.local;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.yumly.core.local.db.AppDatabase;
import com.example.yumly.core.local.db.DAO;
import com.example.yumly.core.models.MealModel;
import java.util.List;

public class MealsLocalDataSource {

    private Context context;
    private DAO dao;
    private LiveData<List<MealModel>> data;
    private static MealsLocalDataSource mealsLocalDataSource = null;

    private MealsLocalDataSource(Context context){
        this.context = context;
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        dao = database.getDAO();
        data = dao.getAllProduct();
    }

    public static MealsLocalDataSource getInstance(Context context){
        if(mealsLocalDataSource == null)
            mealsLocalDataSource = new MealsLocalDataSource(context);
        return mealsLocalDataSource;
    }

    public LiveData<List<MealModel>> getAllData(){
        return data;
    }

    public void delete(MealModel mealModel){
        new Thread(() -> dao.deleteProduct(mealModel)).start();
    }

    public void insert(MealModel mealModel){
        new Thread(() -> dao.addProduct(mealModel)).start();
    }

}
