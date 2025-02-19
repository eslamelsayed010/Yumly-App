package com.example.yumly.features.auth.views.my_view;

import com.example.yumly.core.models.UserModel;

public interface MyLoginView {
    void userInput(String email, String password);
    void onSuccess(UserModel user);
    void onError(String message);
}
