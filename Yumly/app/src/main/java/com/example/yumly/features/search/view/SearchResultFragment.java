package com.example.yumly.features.search.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.yumly.R;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.databinding.FragmentSearchResultBinding;
import com.example.yumly.features.home.presenter.HomePresenter;
import com.example.yumly.features.home.view.MyHomeView;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchResultFragment extends Fragment implements OnSearchResultClickListener, MyHomeView {

    FragmentSearchResultBinding binding;
    ArrayList<MealModel> meals;
    HomePresenter presenter;

    Dialog dialog2;

    public SearchResultFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.arrowBackId.setOnClickListener(v -> arrowBackOnClick());
        initLoadingDialog();
        dialog2.show();
        initMeal();
        initRecycleView();
        initPresenter();
    }

    void initLoadingDialog() {
        dialog2 = new Dialog(getContext());
        dialog2.setContentView(R.layout.loading);
        dialog2.setCancelable(false);
        if (dialog2.getWindow() != null) {
            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void arrowBackOnClick() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        navController.navigateUp();
    }

    void initMeal(){
        meals = new ArrayList<>(Arrays
                .asList(SearchResultFragmentArgs
                        .fromBundle(getArguments())
                        .getMeals()));
        dialog2.dismiss();
    }

    private void initRecycleView() {
        binding.recyclerViewId.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerViewId.setLayoutManager(linearLayoutManager);
        SearchResultAdapter myAdapter = new SearchResultAdapter(getContext(), meals ,this);
        binding.recyclerViewId.setAdapter(myAdapter);
    }

    private void initPresenter() {
        presenter = new HomePresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource.getInstance(requireContext()),
                        MealRemoteDataSource.getInstance())
        );
    }

    @Override
    public void onClick(MealModel mealModel) {
        presenter.getMealDetails(mealModel.getIdMeal());
    }

    @Override
    public void getData(ArrayList<MealModel> meals) {

    }

    @Override
    public void getRandomData(ArrayList<MealModel> meals) {

    }

    @Override
    public void getMealDetails(ArrayList<MealModel> meal) {
        MealModel[] mealArray = meal.toArray(new MealModel[0]);
        SearchResultFragmentDirections.ActionSearchResultFragmentToDetailsFragment action = SearchResultFragmentDirections
                .actionSearchResultFragmentToDetailsFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onError(String msg) {
        dialog2.dismiss();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}