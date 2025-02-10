package com.example.yumly.presenters;

import com.example.yumly.models.UserModel;
import com.example.yumly.views.AuthView;

public class AuthPresenter {
    AuthView view;

    public AuthPresenter(AuthView view) {
        this.view = view;
    }

    // func which connect to firebase
//    public UserModel getDataFromFireBase(){
//
//        return new UserModel("eslam", "0100");
//    }
//
//    //func which get data from firebase
//    public void signInWithGoogle(){
//        view.signInWithGoogle(getDataFromFireBase().getName(), getDataFromFireBase().getPassword());
//    }
}
