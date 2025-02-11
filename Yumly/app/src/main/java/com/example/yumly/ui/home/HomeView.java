package com.example.yumly.ui.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yumly.databinding.FragmentHomeViewBinding;
import com.example.yumly.models.UserModel;

public class HomeView extends Fragment {

    FragmentHomeViewBinding binding;
    UserModel userModel;

    public HomeView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            userModel = HomeViewArgs.fromBundle(getArguments()).getUser();
        }


        binding.text.setText(userModel.getName());
    }
}