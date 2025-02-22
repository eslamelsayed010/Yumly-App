package com.example.yumly.core.models;

import androidx.room.Entity;
import java.io.Serializable;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.yumly.core.converter.MealModelConverter;

@Entity(tableName = "plan_table")
@TypeConverters(MealModelConverter.class)
public class PlanModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String day;
    private String userID;

    private MealModel meal;

    public PlanModel() {
    }

    public PlanModel(String day, String userID, MealModel meal) {
        this.day = day;
        this.userID = userID;
        this.meal = meal;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public MealModel getMeal() {
        return meal;
    }

    public void setMeal(MealModel meal) {
        this.meal = meal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
