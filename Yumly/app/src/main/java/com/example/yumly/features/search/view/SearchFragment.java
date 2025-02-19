package com.example.yumly.features.search.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.yumly.R;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.SearchRepository;
import com.example.yumly.core.models.CountryModel;
import com.example.yumly.databinding.FragmentSearchBinding;
import com.example.yumly.features.search.presenter.SearchPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment implements SearchView{

    FragmentSearchBinding binding;
    ArrayList<CountryModel> countries = new ArrayList<>();
    ArrayList<String> strCategory = new ArrayList<>();
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
        setUpGroupChips();
        handleSearchBTN();
    }

    void handleSearchBTN(){
        TextInputLayout textInputLayout = binding.searchTextFieldId;
        EditText editText = textInputLayout.getEditText();
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterNames(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private void filterNames(String query) {
        List<CountryModel> filteredNames = countries.stream()
                .filter(name -> name.getName().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
        if (filteredNames.isEmpty()) {
            Toast.makeText(getActivity(), "No matches found", Toast.LENGTH_SHORT).show();
        } else {
            GridAdapterChips gridAdapter = new GridAdapterChips(requireContext(), new ArrayList<>(filteredNames));
            binding.recyclerViewId.setAdapter(gridAdapter);
        }
    }

    private void initPresenter() {
        presenter = new SearchPresenter(this, SearchRepository.getInstance(MealRemoteDataSource.getInstance()));
        presenter.getData();
        presenter.getRemoteData();
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
                            GridAdapterCategory gridAdapterCategory = new GridAdapterCategory(requireContext(), strCategory);
                            binding.recyclerViewId.setAdapter(gridAdapterCategory);
                            break;
                        case "Ingredient" :
                            GridAdapterChips gridAdapter = new GridAdapterChips(requireContext(), countries);
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
    public void getCategory(ArrayList<String> strCategory) {
        this.strCategory = strCategory;
        Log.i("TAGe", "getCategory: "+ strCategory.size());
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(MealModel mealModel) {
        //presenter.getRemoteDataByCountry();
    }

    @Override
    public void getDataByCountry(ArrayList<MealModel> countries) {

    }
}