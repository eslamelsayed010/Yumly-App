package com.example.yumly.features.home.view;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yumly.data.models.MealModel;
import com.example.yumly.databinding.FragmentHomeViewBinding;
import com.example.yumly.data.models.UserModel;
import java.util.ArrayList;


public class HomeView extends Fragment {

    FragmentHomeViewBinding binding;
    UserModel userModel;
    ArrayList<MealModel> meals = new ArrayList<>();

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
    //        if (getArguments() != null) {
//            userModel = HomeViewArgs.fromBundle(getArguments()).getUser();
//        }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Add dummy data
        meals.add(new MealModel("Meal 1", "roasted_chicken"));
        meals.add(new MealModel("Meal 2", "roasted_chicken"));
        meals.add(new MealModel("Meal 3", "roasted_chicken"));
        meals.add(new MealModel("Meal 4", "roasted_chicken"));
        meals.add(new MealModel("Meal 5", "roasted_chicken"));
        meals.add(new MealModel("Meal 6", "roasted_chicken"));
        meals.add(new MealModel("Meal 7", "roasted_chicken"));
        meals.add(new MealModel("Meal 8", "roasted_chicken"));

        // Set up RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.recyclerViewId.setLayoutManager(layoutManager);

        GridAdapter gridAdapter = new GridAdapter(requireContext(), meals);
        binding.recyclerViewId.setAdapter(gridAdapter);

        closeApp();
    }

    public void closeApp(){
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish(); // Close the app
            }
        });
    }

}

//        binding.logoutId.setOnClickListener(v -> {
//            FirebaseAuth.getInstance().signOut();
//            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
//            navController.navigateUp();
//        });