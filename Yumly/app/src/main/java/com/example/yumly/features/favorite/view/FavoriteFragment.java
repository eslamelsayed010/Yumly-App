package com.example.yumly.features.favorite.view;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.databinding.FragmentFavoriteBinding;
import com.example.yumly.features.favorite.presenter.FavPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment implements FavView, OnFavClickListener {

    FragmentFavoriteBinding binding;
    FavPresenter presenter;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public FavoriteFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initRecycleView();
        closeApp();
    }

    public void closeApp() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish(); // Close the app
            }
        });
    }

    void initPresenter(){
        presenter = new FavPresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource.getInstance(requireContext()),
                        MealRemoteDataSource.getInstance()));
        presenter.getMeals();
        if (currentUser != null) {
            Log.d("Auth", "User ID: " + currentUser.getUid());
            presenter.insertAllFavoriteFromFirebase(currentUser.getUid(), "favorite");
        } else {
            Log.e("Auth", "User is null. Cannot fetch plans.");
        }
    }

    private void initRecycleView() {
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getMeals(ArrayList<MealModel> models) {
        myAdapter = new MyAdapter(getContext(), models, this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void getError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRemoveFromFav(MealModel mealModel) {
        Toast.makeText(getContext(), "Remove "  + mealModel.getStrMeal() + " from Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(MealModel mealModel) {
        presenter.removeProduct(mealModel, currentUser.getUid());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(MealModel mealModel) {
        ArrayList<MealModel> meal = new ArrayList<>();
        meal.add(mealModel);
        MealModel[] mealArray = meal.toArray(new MealModel[0]);
        FavoriteFragmentDirections.ActionFavoriteFragmentToDetailsFragment action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }
}