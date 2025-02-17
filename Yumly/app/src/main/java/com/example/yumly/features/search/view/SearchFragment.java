package com.example.yumly.features.search.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yumly.R;
import com.example.yumly.core.repo.CountryRepository;
import com.example.yumly.data.models.CountryModel;
import com.example.yumly.databinding.FragmentSearchBinding;
import com.example.yumly.features.home.view.GridAdapter;
import com.example.yumly.features.search.presenter.SearchPresenter;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchView{

    FragmentSearchBinding binding;
    ArrayList<CountryModel> countries = new ArrayList<>();
    SearchPresenter presenter;


    public SearchFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.arrowBackId.setOnClickListener(v-> arrowBackOnClick());
        initGridView();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new SearchPresenter(this, CountryRepository.getInstance());
        presenter.getData();
        setUpGroupChips();
    }

    private void arrowBackOnClick() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        navController.navigateUp();
    }

    private void initGridView() {
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 3);
        binding.recyclerViewId.setLayoutManager(layoutManager);
    }

    private void setUpGroupChips(){
        binding.chipGroupFilter.setSingleSelection(true);
        for (int i=0; i<binding.chipGroupFilter.getChildCount(); i++){
            Chip chip = (Chip) binding.chipGroupFilter.getChildAt(i);
            chip.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    switch (chip.getText().toString()){
                        case "Category" :
                            GridAdapterChips gridAdapter = new GridAdapterChips(requireContext(), countries);
                            binding.recyclerViewId.setAdapter(gridAdapter);
                            break;
                        case "Ingredient" :
                            gridAdapter = new GridAdapterChips(requireContext(), countries);
                            binding.recyclerViewId.setAdapter(gridAdapter);
                            break;
                        case "Country" :
                            gridAdapter = new GridAdapterChips(requireContext(), countries);
                            binding.recyclerViewId.setAdapter(gridAdapter);
                            break;
                    }
                    Toast.makeText(getActivity(), chip.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public void getData(ArrayList<CountryModel> countries) {
        this.countries = countries;
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}