package com.example.yumly.core.repo;

import com.example.yumly.R;
import com.example.yumly.core.models.CountryModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.remote.NetworkCallback;

import java.util.ArrayList;

public class SearchRepository {

    private static SearchRepository searchRepository = null;
    MealRemoteDataSource remoteDataSource;

    ArrayList<CountryModel> countries = new ArrayList<>();

    private SearchRepository(MealRemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
        fillCountryList();
    }

    public static SearchRepository getInstance(MealRemoteDataSource remoteDataSource){
        if (searchRepository == null)
            searchRepository = new SearchRepository(remoteDataSource);
        return searchRepository;
    }

    void fillCountryList(){
        countries.add(new CountryModel("Egyptian", R.drawable.egypt));
        countries.add(new CountryModel("American", R.drawable.america));
        countries.add(new CountryModel("British", R.drawable.britain));
        countries.add(new CountryModel("Canadian", R.drawable.canada));
        countries.add(new CountryModel("Chinese", R.drawable.china));
        countries.add(new CountryModel("Croatian", R.drawable.coratia));
        countries.add(new CountryModel("Dutch", R.drawable.dutch));
        countries.add(new CountryModel("French", R.drawable.france));
        countries.add(new CountryModel("Greek", R.drawable.greece));
        countries.add(new CountryModel("Indian", R.drawable.india));
        countries.add(new CountryModel("Irish", R.drawable.ireland));
        countries.add(new CountryModel("Italian", R.drawable.italy));
        countries.add(new CountryModel("Jamaican", R.drawable.jamaica));
        countries.add(new CountryModel("Japanese", R.drawable.japan));
        countries.add(new CountryModel("Kenyan", R.drawable.kenya));
        countries.add(new CountryModel("Malaysian", R.drawable.malaysia));
        countries.add(new CountryModel("Mexican", R.drawable.mexico));
        countries.add(new CountryModel("Moroccan", R.drawable.morocco));
        countries.add(new CountryModel("Polish", R.drawable.poland));
        countries.add(new CountryModel("Portuguese", R.drawable.portugal));
        countries.add(new CountryModel("Russian", R.drawable.russia));
        countries.add(new CountryModel("Spanish", R.drawable.spain));
        countries.add(new CountryModel("Thai", R.drawable.thailand));
        countries.add(new CountryModel("Tunisian", R.drawable.tunisia));
        countries.add(new CountryModel("Turkish", R.drawable.turkey));
        countries.add(new CountryModel("Ukrainian", R.drawable.ukraine));
        countries.add(new CountryModel("Uruguayan", R.drawable.uruguay));
        countries.add(new CountryModel("Vietnamese", R.drawable.vietnam));
    }

    public void getRemoteData(NetworkCallback networkCallback){
        remoteDataSource.getStrCategoryResponse(networkCallback);
    }

    public ArrayList<CountryModel> getCountries() {
        return countries;
    }

    public void getRemoteMealByName(NetworkCallback networkCallback,String country){
        remoteDataSource.getMealByCountry(networkCallback, country);
    }
}
