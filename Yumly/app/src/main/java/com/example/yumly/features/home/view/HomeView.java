package com.example.yumly.features.home.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.databinding.FragmentHomeViewBinding;
import com.example.yumly.features.home.presenter.HomePresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeView extends Fragment implements MyHomeView, OnItemClickListenerHome {

    FragmentHomeViewBinding binding;
    ArrayList<MealModel> meals = new ArrayList<>();
    ArrayList<MealModel> randomMeals = new ArrayList<>();
    HomePresenter presenter;
    GridAdapter gridAdapter;
    Dialog dialog;

    public HomeView() {
    }

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
        initGridView();
        initPresenter();
        handleOnClickListener();
        initDialog();
        closeApp();
        if (meals.isEmpty())
            dialog.show();
    }

    void initDialog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.loading);
        dialog.setCancelable(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    void handleOnClickListener() {
        binding.searchContainerId.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.action_homeView_to_searchFragment));
        binding.mealOfTheDayImageId.setOnClickListener(v -> getMealDetails(randomMeals));
    }

    private void initPresenter() {
        presenter = new HomePresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource.getInstance(requireContext()),
                        MealRemoteDataSource.getInstance())
        );
        presenter.getAllMeals();
        presenter.getRandomMeal();
    }

    private void initGridView() {
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false);
        binding.recyclerViewId.setLayoutManager(layoutManager);
    }

    public void closeApp() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish(); // Close the app
            }
        });
    }

    @Override
    public void getData(ArrayList<MealModel> meals) {
        meals.remove(0);
        this.meals = meals;
        dialog.dismiss();
        gridAdapter = new GridAdapter(requireContext(), meals, this);
        binding.recyclerViewId.setAdapter(gridAdapter);
    }

    @Override
    public void getRandomData(ArrayList<MealModel> meals) {
        randomMeals = meals;
        Glide.with(getContext()).load(meals.get(0).getStrMealThumb()).into(binding.mealOfTheDayImageId);
    }

    @Override
    public void getMealDetails(ArrayList<MealModel> meal) {
        MealModel[] mealArray = meal.toArray(new MealModel[0]);
        HomeViewDirections.ActionHomeViewToDetailsFragment action = HomeViewDirections.actionHomeViewToDetailsFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onError(String msg) {
        dialog.dismiss();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onError: " + msg);
    }

    @Override
    public void onClick(MealModel mealModel) {
        presenter.getMealDetails(mealModel.getIdMeal());
    }

}