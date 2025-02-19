package com.example.yumly.core.remote;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatResponse {

    @SerializedName("meals")
    private ArrayList<String> cat;

    public CatResponse(ArrayList<String> cat) {
        this.cat = cat;
    }

    public ArrayList<String> getCat() {
        return cat;
    }

    public void setProducts(ArrayList<String> cat) {
        this.cat = cat;
    }
}
