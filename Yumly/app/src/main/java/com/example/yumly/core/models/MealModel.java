package com.example.yumly.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "meal_table")
public class MealModel implements Parcelable {

    @PrimaryKey
    @NonNull
    private String idMeal;
    @Nullable
    private String strMeal;
    @Nullable
    private String strCategory;
    @Nullable
    private String strArea;
    @Nullable
    private String strInstructions;
    @Nullable
    private String strMealThumb;
    @Nullable
    private String strYoutube;
    @Nullable
    private String strIngredient1;
    @Nullable
    private String strIngredient2;
    @Nullable
    private String strIngredient3;
    @Nullable
    private String strIngredient4;
    @Nullable
    private String strIngredient5;
    @Nullable
    private String strIngredient6;
    @Nullable
    private String strIngredient7;
    @Nullable
    private String strIngredient8;
    @Nullable
    private String strIngredient9;
    @Nullable
    private String strIngredient10;
    @Nullable
    private String strIngredient11;
    @Nullable
    private String strIngredient12;
    @Nullable
    private String strIngredient13;
    @Nullable
    private String strIngredient14;
    @Nullable
    private String strIngredient15;
    @Nullable
    private String strIngredient16;
    @Nullable
    private String strIngredient17;
    @Nullable
    private String strIngredient18;
    @Nullable
    private String strIngredient19;
    @Nullable
    private String strIngredient20;
    @Nullable
    private String strMeasure1;
    @Nullable
    private String strMeasure2;
    @Nullable
    private String strMeasure3;
    @Nullable
    private String strMeasure4;
    @Nullable
    private String strMeasure5;
    @Nullable
    private String strMeasure6;
    @Nullable
    private String strMeasure7;
    @Nullable
    private String strMeasure8;
    @Nullable
    private String strMeasure9;
    @Nullable
    private String strMeasure10;
    @Nullable
    private String strMeasure11;
    @Nullable
    private String strMeasure12;
    @Nullable
    private String strMeasure13;
    @Nullable
    private String strMeasure14;
    @Nullable
    private String strMeasure15;
    @Nullable
    private String strMeasure16;
    @Nullable
    private String strMeasure17;
    @Nullable
    private String strMeasure18;
    @Nullable
    private String strMeasure19;
    @Nullable
    private String strMeasure20;

    public MealModel() {}


    public MealModel(String idMeal,
                     String strMeal,
                     String strCategory,
                     String strArea,
                     String strInstructions,
                     String strMealThumb,
                     String strYoutube,
                     String strIngredient1,
                     String strIngredient2,
                     String strIngredient3,
                     String strIngredient4,
                     String strIngredient5,
                     String strIngredient6,
                     String strIngredient7,
                     String strIngredient8,
                     String strIngredient9,
                     String strIngredient10,
                     String strIngredient11,
                     String strIngredient12,
                     String strIngredient13,
                     String strIngredient14,
                     String strIngredient15,
                     String strIngredient16,
                     String strIngredient17,
                     String strIngredient18,
                     String strIngredient19,
                     String strIngredient20,
                     String strMeasure20,
                     String strMeasure19,
                     String strMeasure18,
                     String strMeasure17,
                     String strMeasure16,
                     String strMeasure15,
                     String strMeasure14,
                     String strMeasure13,
                     String strMeasure12,
                     String strMeasure11,
                     String strMeasure10,
                     String strMeasure9,
                     String strMeasure8,
                     String strMeasure7,
                     String strMeasure6,
                     String strMeasure5,
                     String strMeasure4,
                     String strMeasure3,
                     String strMeasure2,
                     String strMeasure1
    ) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure20 = strMeasure20;
        this.strMeasure19 = strMeasure19;
        this.strMeasure18 = strMeasure18;
        this.strMeasure17 = strMeasure17;
        this.strMeasure16 = strMeasure16;
        this.strMeasure15 = strMeasure15;
        this.strMeasure14 = strMeasure14;
        this.strMeasure13 = strMeasure13;
        this.strMeasure12 = strMeasure12;
        this.strMeasure11 = strMeasure11;
        this.strMeasure10 = strMeasure10;
        this.strMeasure9 = strMeasure9;
        this.strMeasure8 = strMeasure8;
        this.strMeasure7 = strMeasure7;
        this.strMeasure6 = strMeasure6;
        this.strMeasure5 = strMeasure5;
        this.strMeasure4 = strMeasure4;
        this.strMeasure3 = strMeasure3;
        this.strMeasure2 = strMeasure2;
        this.strMeasure1 = strMeasure1;
    }


    protected MealModel(Parcel in) {
        idMeal = in.readString();
        strMeal = in.readString();
        strCategory = in.readString();
        strArea = in.readString();
        strInstructions = in.readString();
        strMealThumb = in.readString();
        strYoutube = in.readString();
        strIngredient1 = in.readString();
        strIngredient2 = in.readString();
        strIngredient3 = in.readString();
        strIngredient4 = in.readString();
        strIngredient5 = in.readString();
        strIngredient6 = in.readString();
        strIngredient7 = in.readString();
        strIngredient8 = in.readString();
        strIngredient9 = in.readString();
        strIngredient10 = in.readString();
        strIngredient11 = in.readString();
        strIngredient12 = in.readString();
        strIngredient13 = in.readString();
        strIngredient14 = in.readString();
        strIngredient15 = in.readString();
        strIngredient16 = in.readString();
        strIngredient17 = in.readString();
        strIngredient18 = in.readString();
        strIngredient19 = in.readString();
        strIngredient20 = in.readString();
        strMeasure20 = in.readString();
        strMeasure19 = in.readString();
        strMeasure18 = in.readString();
        strMeasure17 = in.readString();
        strMeasure16 = in.readString();
        strMeasure15 = in.readString();
        strMeasure14 = in.readString();
        strMeasure13 = in.readString();
        strMeasure12 = in.readString();
        strMeasure11 = in.readString();
        strMeasure10 = in.readString();
        strMeasure9 = in.readString();
        strMeasure8 = in.readString();
        strMeasure7 = in.readString();
        strMeasure6 = in.readString();
        strMeasure5 = in.readString();
        strMeasure4 = in.readString();
        strMeasure3 = in.readString();
        strMeasure2 = in.readString();
        strMeasure1 = in.readString();
    }

    public static final Creator<MealModel> CREATOR = new Creator<MealModel>() {
        @Override
        public MealModel createFromParcel(Parcel in) {
            return new MealModel(in);
        }

        @Override
        public MealModel[] newArray(int size) {
            return new MealModel[size];
        }
    };

    // Getters and Setters
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    @Nullable
    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(@Nullable String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    @Nullable
    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(@Nullable String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    @Nullable
    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(@Nullable String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    @Nullable
    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(@Nullable String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    @Nullable
    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(@Nullable String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    @Nullable
    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(@Nullable String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    @Nullable
    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(@Nullable String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    @Nullable
    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(@Nullable String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    @Nullable
    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(@Nullable String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    @Nullable
    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(@Nullable String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    @Nullable
    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(@Nullable String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    @Nullable
    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(@Nullable String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    @Nullable
    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(@Nullable String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    @Nullable
    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(@Nullable String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    @Nullable
    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(@Nullable String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    @Nullable
    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(@Nullable String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    @Nullable
    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(@Nullable String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    @Nullable
    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(@Nullable String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    @Nullable
    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(@Nullable String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    @Nullable
    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(@Nullable String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    @NonNull
    @Override
    public String toString() {
        return "Meal{" + "idMeal='" + idMeal + '\'' + ", strMeal='" + strMeal + '\'' + ", strCategory='" + strCategory + '\'' + ", strArea='" + strArea + '\'' + ", strInstructions='" + strInstructions + '\'' + ", strMealThumb='" + strMealThumb + '\'' + ", strYoutube='" + strYoutube + '\'' + ", strIngredient1='" + strIngredient1 + '\'' + ", strIngredient2='" + strIngredient2 + '\'' + ", strIngredient3='" + strIngredient3 + '\'' + ", strIngredient4='" + strIngredient4 + '\'' + ", strIngredient5='" + strIngredient5 + '\'' + ", strIngredient6='" + strIngredient6 + '\'' + ", strIngredient7='" + strIngredient7 + '\'' + ", strIngredient8='" + strIngredient8 + '\'' + ", strIngredient9='" + strIngredient9 + '\'' + ", strIngredient10='" + strIngredient10 + '\'' + ", strIngredient11='" + strIngredient11 + '\'' + ", strIngredient12='" + strIngredient12 + '\'' + ", strIngredient13='" + strIngredient13 + '\'' + ", strIngredient14='" + strIngredient14 + '\'' + ", strIngredient15='" + strIngredient15 + '\'' + ", strIngredient16='" + strIngredient16 + '\'' + ", strIngredient17='" + strIngredient17 + '\'' + ", strIngredient18='" + strIngredient18 + '\'' + ", strIngredient19='" + strIngredient19 + '\'' + ", strIngredient20='" + strIngredient20 + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idMeal);
        dest.writeString(strMeal);
        dest.writeString(strCategory);
        dest.writeString(strArea);
        dest.writeString(strInstructions);
        dest.writeString(strMealThumb);
        dest.writeString(strYoutube);
        dest.writeString(strIngredient1);
        dest.writeString(strIngredient2);
        dest.writeString(strIngredient3);
        dest.writeString(strIngredient4);
        dest.writeString(strIngredient5);
        dest.writeString(strIngredient6);
        dest.writeString(strIngredient7);
        dest.writeString(strIngredient8);
        dest.writeString(strIngredient9);
        dest.writeString(strIngredient10);
        dest.writeString(strIngredient11);
        dest.writeString(strIngredient12);
        dest.writeString(strIngredient13);
        dest.writeString(strIngredient14);
        dest.writeString(strIngredient15);
        dest.writeString(strIngredient16);
        dest.writeString(strIngredient17);
        dest.writeString(strIngredient18);
        dest.writeString(strIngredient19);
        dest.writeString(strIngredient20);
        dest.writeString(strMeasure1);
        dest.writeString(strMeasure2);
        dest.writeString(strMeasure3);
        dest.writeString(strMeasure4);
        dest.writeString(strMeasure5);
        dest.writeString(strMeasure6);
        dest.writeString(strMeasure7);
        dest.writeString(strMeasure8);
        dest.writeString(strMeasure9);
        dest.writeString(strMeasure10);
        dest.writeString(strMeasure11);
        dest.writeString(strMeasure12);
        dest.writeString(strMeasure13);
        dest.writeString(strMeasure14);
        dest.writeString(strMeasure15);
        dest.writeString(strMeasure16);
        dest.writeString(strMeasure17);
        dest.writeString(strMeasure18);
        dest.writeString(strMeasure19);
        dest.writeString(strMeasure20);
    }

    public List<IngredientDetailsModel> getIngredientList() {
        List<IngredientDetailsModel> ingredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            try {
                String ingredient = (String) this.getClass().getDeclaredMethod("getStrIngredient" + i).invoke(this);
                String measure = (String) this.getClass().getDeclaredMethod("getStrMeasure" + i).invoke(this);

                if (ingredient != null && !ingredient.trim().isEmpty() && measure != null && !measure.trim().isEmpty()) {
                    ingredients.add(new IngredientDetailsModel(ingredient, measure));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ingredients;
    }
}
