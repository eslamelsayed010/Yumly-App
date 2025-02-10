package com.example.yumly.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.databinding.SplashViewBinding;
import com.example.yumly.ui.auth.SignupUi;
import com.example.yumly.views.AuthView;

public class MainActivity extends AppCompatActivity implements AuthView {

    //AuthPresenter presenter;
    SplashViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = SplashViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, SignupUi.class);
            startActivity(intent);
        },5000);

        //presenter = new AuthPresenter(this);

//        binding.btnId.setOnClickListener(v -> {
//            Intent intent = new Intent(this, SignupUi.class);
//            startActivity(intent);
//            presenter.login();
//        });

    }

    @Override
    public void onGetData(String name, String password) {
        //binding.txtId.setText(name);
    }
}