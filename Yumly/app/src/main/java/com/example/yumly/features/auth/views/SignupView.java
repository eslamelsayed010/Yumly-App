package com.example.yumly.features.auth.views;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yumly.R;
import com.example.yumly.databinding.FragmentSignupViewBinding;
import com.example.yumly.core.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupView extends Fragment {

    FragmentSignupViewBinding binding;
    private FirebaseAuth mAuth;

    UserModel user;

    public SignupView() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        binding.signupWithEmailId.setOnClickListener(v -> signUpUser(view));
        binding.arrowBackId.setOnClickListener(v-> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
            navController.navigateUp();
        });
    }

    private void signUpUser(View view) {
        String name = binding.nameTextFieldId.getEditText().getText().toString().trim();
        String email = binding.emailTextFieldId.getEditText().getText().toString().trim();
        String password = binding.passwordTextFieldId.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase sign-up
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign up success
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(requireContext(), "Signup successful!", Toast.LENGTH_SHORT).show();
                        this.user = new UserModel(name, user.getEmail(),user.getUid());
                        SignupViewDirections.ActionSignupViewToHomeView action = SignupViewDirections.actionSignupViewToHomeView(this.user);
                        Navigation.findNavController(view).navigate(action);
                    } else {
                        // If sign up fails, display a message to the user
                        Toast.makeText(requireContext(), "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}