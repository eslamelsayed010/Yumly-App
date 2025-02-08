package com.example.yumly.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yumly.presenters.AuthPresenter;
import com.example.yumly.ui.auth.SignupUi;
import com.example.yumly.views.AuthView;
import com.example.yumly.R;
public class MainActivity extends AppCompatActivity implements AuthView {

    TextView textView;
    Button button;
    AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_id);
        button = findViewById(R.id.btn_id);

        presenter = new AuthPresenter(this);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupUi.class);
            startActivity(intent);
            presenter.login();
        });
    }

    @Override
    public void onGetData(String name, String password) {
        textView.setText(name);
    }
}