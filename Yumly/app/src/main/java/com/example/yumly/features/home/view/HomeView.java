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
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.data.models.MealModel;
import com.example.yumly.databinding.FragmentHomeViewBinding;
import com.example.yumly.data.models.UserModel;
import com.example.yumly.features.home.presenter.HomePresenter;

import java.util.ArrayList;


public class HomeView extends Fragment implements MyHomeView{

    FragmentHomeViewBinding binding;
    UserModel userModel;
    ArrayList<MealModel> meals = new ArrayList<>();
    HomePresenter presenter;

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
        initGridView();
        initPresenter();
        closeApp();
    }

    private void initPresenter() {
        presenter = new HomePresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource.getInstance(requireContext()),
                        MealRemoteDataSource.getInstance())
        );
        presenter.getData();
    }

    private void initGridView() {
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.recyclerViewId.setLayoutManager(layoutManager);
    }

    public void closeApp(){
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish(); // Close the app
            }
        });
    }

    @Override
    public void getData(ArrayList<MealModel> meals) {
        this.meals = meals;
        GridAdapter gridAdapter = new GridAdapter(requireContext(), meals);
        binding.recyclerViewId.setAdapter(gridAdapter);
    }

    @Override
    public void getRandomData(ArrayList<MealModel> meals) {
        Glide.with(getContext()).load(meals.get(0).getStrMealThumb()).into(binding.mealOfTheDayImageId);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}

//        binding.logoutId.setOnClickListener(v -> {
//            FirebaseAuth.getInstance().signOut();
//            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
//            navController.navigateUp();
//        });

//        if (getArguments() != null) {
//            userModel = HomeViewArgs.fromBundle(getArguments()).getUser();
//        }