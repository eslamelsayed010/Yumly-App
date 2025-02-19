package com.example.yumly;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.databinding.FragmentSearchResultBinding;
import com.example.yumly.features.search.view.SearchResultAdapter;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchResultFragment extends Fragment {

    FragmentSearchResultBinding binding;
    ArrayList<MealModel> meals;
    SearchResultAdapter searchResultAdapter;

    public SearchResultFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMeal();
        initRecycleView();
    }

    void initMeal(){
        meals = new ArrayList<>(Arrays
                .asList(SearchResultFragmentArgs
                        .fromBundle(getArguments())
                        .getMeals()));
    }

    private void initRecycleView() {
        binding.recyclerViewId.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerViewId.setLayoutManager(linearLayoutManager);
        SearchResultAdapter myAdapter = new SearchResultAdapter(getContext(), meals);
        binding.recyclerViewId.setAdapter(myAdapter);
    }


}