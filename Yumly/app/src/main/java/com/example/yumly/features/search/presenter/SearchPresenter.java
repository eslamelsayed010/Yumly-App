package com.example.yumly.features.search.presenter;

import com.example.yumly.core.repo.CountryRepository;
import com.example.yumly.data.models.CountryModel;
import com.example.yumly.features.search.view.SearchView;

import java.util.ArrayList;

public class SearchPresenter {

    SearchView view;
    CountryRepository repo;

    public SearchPresenter(SearchView view, CountryRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public ArrayList<CountryModel> getData(){
        view.getData(repo.getCountries());
        return repo.getCountries();
    }


    public void onSuccess(ArrayList<CountryModel> countries) {
        view.getData(repo.getCountries());
    }

    public void onFailure(String errorMessage) {
        view.onError(errorMessage);
    }


}

