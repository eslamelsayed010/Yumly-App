package com.example.yumly.features.auth.views.my_view;

import com.example.yumly.data.models.UserModel;

public interface GoogleView {
    void onError(String message);
    void onSuccess(UserModel user);
}
