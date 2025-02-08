package com.example.yumly.presenters;

import com.example.yumly.views.AuthView;
import com.example.yumly.models.AuthModel;

public class AuthPresenter {
    AuthView view;

    public AuthPresenter(AuthView view) {
        this.view = view;
    }

    // func which connect to firebase
    public AuthModel getDataFromFireBase(){
        return new AuthModel("eslam", "0100");
    }

    //func which get data from firebase
    public void login(){
        view.onGetData(getDataFromFireBase().getName(), getDataFromFireBase().getPassword());
    }
}
