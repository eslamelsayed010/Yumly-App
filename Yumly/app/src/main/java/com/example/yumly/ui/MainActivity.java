package com.example.yumly.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.databinding.ActivityMainBinding;
import com.example.yumly.presenters.AuthPresenter;
import com.example.yumly.ui.auth.SignupUi;
import com.example.yumly.views.AuthView;

public class MainActivity extends AppCompatActivity implements AuthView {

    AuthPresenter presenter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new AuthPresenter(this);

        binding.btnId.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupUi.class);
            startActivity(intent);
            presenter.login();
        });
    }

    @Override
    public void onGetData(String name, String password) {
        binding.txtId.setText(name);
    }
}