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
import com.example.yumly.databinding.FragmentLoginViewBinding;
import com.example.yumly.core.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginView extends Fragment {

    FragmentLoginViewBinding binding;
    private FirebaseAuth mAuth;

    UserModel user;

    public LoginView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        binding.btnLoginWithEmailId.setOnClickListener(v -> loginUser(view));
        binding.arrowBackId.setOnClickListener(v-> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
            navController.navigateUp();
        });
    }

    private void loginUser(View view) {
        String email = binding.loginEmailTextFieldId.getEditText().getText().toString().trim();
        String password = binding.loginPasswordTextFieldId.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        String name = email.split("@")[0];
                        this.user = new UserModel(name, user.getEmail(),user.getUid());
                        LoginViewDirections.ActionLoginViewToHomeView action = LoginViewDirections.actionLoginViewToHomeView(this.user);
                        Navigation.findNavController(view).navigate(action);
                    } else {
                        Toast.makeText(requireContext(), "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}