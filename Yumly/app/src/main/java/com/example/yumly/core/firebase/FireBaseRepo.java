package com.example.yumly.core.firebase;

import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import com.google.firebase.database.DatabaseReference;

public class FireBaseRepo {
    private FirebaseDataSource firebaseDatastore;
    private static FireBaseRepo instance;
    private FireBaseRepo() {
        firebaseDatastore = FirebaseDataSource.getInstance();
    }
    public static FireBaseRepo getInstance() {
        if (instance == null) {
            instance = new FireBaseRepo();
        }
        return instance;
    }



    public  void insert(PlanModel planModel, String type){
        firebaseDatastore.insert(planModel, type);
    }

    public  void insertFav(MealModel mealModel, String type, String userID){
        firebaseDatastore.insertFav(mealModel, type, userID);
    }
    public void deleteFromFireBase(String userID, MealModel meal, String day, String type) {
            firebaseDatastore.deleteFromFireBase(userID, meal, day, type);
    }
    public DatabaseReference getAllPlansForUser(String userID, String type) {
        return firebaseDatastore.getAllPlansForUser(userID, type);
    }
    public DatabaseReference getFavorite(String userID, String type) {
        return firebaseDatastore.getFavorite(userID,type);
    }

}
