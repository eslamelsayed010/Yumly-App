package com.example.yumly.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CatModel implements Parcelable {

    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;

    public CatModel(String strCategory, String strCategoryThumb, String strCategoryDescription, String idCategory) {
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = strCategoryDescription;
        this.idCategory = idCategory;
    }

    protected CatModel(Parcel in) {
        idCategory = in.readString();
        strCategory = in.readString();
        strCategoryThumb = in.readString();
        strCategoryDescription = in.readString();
    }

    public static final Creator<CatModel> CREATOR = new Creator<CatModel>() {
        @Override
        public CatModel createFromParcel(Parcel in) {
            return new CatModel(in);
        }

        @Override
        public CatModel[] newArray(int size) {
            return new CatModel[size];
        }
    };

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idCategory);
        dest.writeString(strCategory);
        dest.writeString(strCategoryThumb);
        dest.writeString(strCategoryDescription);
    }
}
