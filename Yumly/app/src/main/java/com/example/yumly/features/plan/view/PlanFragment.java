package com.example.yumly.features.plan.view;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.databinding.FragmentPlanBinding;
import com.example.yumly.features.favorite.view.FavoriteFragmentDirections;
import com.example.yumly.features.plan.presenter.PlanPresenter;
import java.util.ArrayList;

public class PlanFragment extends Fragment implements PlanView, OnPlanClickListener {

    FragmentPlanBinding binding;
    PlanPresenter presenter;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public PlanFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closeApp();
        initPresenter();
        initRecycleView();
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
        presenter = new PlanPresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource.getInstance(requireContext()),
                        MealRemoteDataSource.getInstance()));
        presenter.getMeals();
    }

    private void initRecycleView() {
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getMeals(ArrayList<PlanModel> models) {
        myAdapter = new MyAdapter(getContext(), models, this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void getError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRemoveFromPlan(MealModel mealModel) {
        Toast.makeText(getContext(), "Remove "  + mealModel.getStrMeal() + " from Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(PlanModel planModel) {
        presenter.removePlane(planModel);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(MealModel mealModel) {
        ArrayList<MealModel> meal = new ArrayList<>();
        meal.add(mealModel);
        MealModel[] mealArray = meal.toArray(new MealModel[0]);
        PlanFragmentDirections.ActionPlanFragmentToDetailsFragment action = PlanFragmentDirections.actionPlanFragmentToDetailsFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }
}