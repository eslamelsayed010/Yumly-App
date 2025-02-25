package com.example.yumly.features.profile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yumly.R;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    Dialog dialog;
    Button cancelBtn;
    Button logoutBtn;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialog(view);
        initUi();
        closeApp();
    }

    @SuppressLint("SetTextI18n")
    private void initUi() {
        if (currentUser == null) {
            binding.logout.setText("Login");
            binding.userName.setText("Welcome, ");
            binding.logout.setOnClickListener(v -> {
                Navigation.findNavController(getView()).navigate(R.id.action_profileFragment_to_authMenu);
                Navigation.findNavController(getView()).popBackStack(R.id.profileFragment, true);
            });
        } else {
            binding.logout.setText("Logout");
            if (currentUser.getDisplayName() != null)
                binding.userName.setText("Welcome, " + currentUser.getDisplayName());
            else
                binding.userName.setVisibility(View.GONE);
            binding.logout.setOnClickListener(v -> dialog.show());
        }
    }

    private void initDialog(@NonNull View view) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);
        cancelBtn = dialog.findViewById(R.id.dialog_cancel);
        logoutBtn = dialog.findViewById(R.id.dialog_confirm);
        cancelBtn.setOnClickListener(v -> dialog.dismiss());
        logoutBtn.setOnClickListener(v -> {
            MealsRepository.getInstance(MealsLocalDataSource.getInstance(getContext()),
                    MealRemoteDataSource.getInstance()).deleteAll();
            FirebaseAuth.getInstance().signOut();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_authMenu);
            Navigation.findNavController(view).popBackStack(R.id.profileFragment, true);
            dialog.dismiss();
        });
    }

    public void closeApp() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish(); // Close the app
            }
        });
    }
}