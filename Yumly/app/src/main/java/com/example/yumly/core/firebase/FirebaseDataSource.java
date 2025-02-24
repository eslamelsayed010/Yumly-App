package com.example.yumly.core.firebase;

import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDataSource {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static FirebaseDataSource instance;

    private FirebaseDataSource() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("meal");
    }

    public static FirebaseDataSource getInstance() {
        if (instance == null) {
            instance = new FirebaseDataSource();
        }
        return instance;
    }

    public void insert(PlanModel planModel, String type) {
        myRef.child(planModel.getUserID())
                .child(type)
                .child(planModel.getMeal().getIdMeal())
                .setValue(planModel);

    }

    public void insertFav(MealModel mealModel, String type, String userID) {
        myRef.child(userID)
                .child(type)
                .child(mealModel.getIdMeal())
                .setValue(mealModel);

    }

    public void deleteFromFireBase(String userID, MealModel meal, String day, String type) {
        myRef.child(userID)
                .child(type)
                .child(day)
                .child(meal.getIdMeal())
                .removeValue();
    }

    public DatabaseReference getAllPlansForUser(String userID, String type) {
        return myRef.child(userID)
                .child(type);
    }

    public DatabaseReference getFavorite(String userID, String type) {

        return myRef.child(userID)
                .child(type);
    }

}
