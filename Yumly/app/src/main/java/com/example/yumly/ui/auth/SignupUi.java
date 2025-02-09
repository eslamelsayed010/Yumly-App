package com.example.yumly.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.databinding.ActivitySignupUiBinding;

public class SignupUi extends AppCompatActivity {

    ActivitySignupUiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignupUiBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.textButtonLogInId.setOnClickListener(v -> {
            Intent intent = new Intent(SignupUi.this, Login.class);
            startActivity(intent);
        });

        binding.signupBtnId.setOnClickListener(v -> {
            Intent intent = new Intent(SignupUi.this, SignupWithEmail.class);
            startActivity(intent);
        });
    }
}