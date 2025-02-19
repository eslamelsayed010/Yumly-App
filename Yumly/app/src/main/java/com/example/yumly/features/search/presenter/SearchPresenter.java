package com.example.yumly.features.search.presenter;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.NetworkCallback;
import com.example.yumly.core.repo.SearchRepository;
import com.example.yumly.core.models.CountryModel;
import com.example.yumly.features.search.view.SearchView;

import java.util.ArrayList;

public class SearchPresenter implements NetworkCallback {

    SearchView view;
    SearchRepository repo;
    ArrayList<CountryModel> countries = new ArrayList<>();

    public SearchPresenter(SearchView view, SearchRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public ArrayList<CountryModel> getData(){
        view.getData(repo.getCountries());
        return repo.getCountries();
    }


    public ArrayList<CountryModel> getCountries(ArrayList<CountryModel> countries) {
        return repo.getCountries();
    }

    public void getCategory(){
        repo.getRemoteCategory(this);
    }

    public void getRemoteDataByCountry(String country){
        repo.getRemoteMealByName(this, country);
    }

    public void getRemoteDataByCategory(String cat){
        repo.getRemoteMealByCategory(this, cat);
    }

    @Override
    public void onSuccess(ArrayList<MealModel> meals) {}

    @Override
    public void onSuccessGetMealByCountry(ArrayList<MealModel> meals) {
        view.getDataByCountry(meals);
    }

    @Override
    public void onSuccessGetMealByCategory(ArrayList<MealModel> meals) {
        view.getDataByCategory(meals);
    }

    public void onFailure(String errorMessage) {
        view.onError(errorMessage);
    }

    @Override
    public void onSuccessRandom(ArrayList<MealModel> meals) {}

    @Override
    public void onSuccessGetCat(ArrayList<CatModel> cats) {
        view.getCategory(cats);
    }


}

