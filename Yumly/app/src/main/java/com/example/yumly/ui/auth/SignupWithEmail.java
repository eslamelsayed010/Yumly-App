package com.example.yumly.ui.auth;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.databinding.ActivitySignupWithEmailBinding;

public class SignupWithEmail extends AppCompatActivity {

    ActivitySignupWithEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignupWithEmailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

    }
}