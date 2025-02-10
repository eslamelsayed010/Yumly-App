package com.example.yumly.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.databinding.ActivitySignupWithEmailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupWithEmail extends AppCompatActivity {

    private ActivitySignupWithEmailBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignupWithEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        binding.signupWithEmailId.setOnClickListener(v -> signUpUser());
    }

    private void signUpUser() {
        String name = binding.nameTextFieldId.getEditText().getText().toString().trim();
        String email = binding.emailTextFieldId.getEditText().getText().toString().trim();
        String password = binding.passwordTextFieldId.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase sign-up
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up success
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignupWithEmail.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                        // Optionally, send email verification
                        if (user != null) {
                            user.sendEmailVerification();
                        }
                    } else {
                        // If sign up fails, display a message to the user
                        Toast.makeText(SignupWithEmail.this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
