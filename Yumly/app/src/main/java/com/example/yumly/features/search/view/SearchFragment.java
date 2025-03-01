package com.example.yumly.features.search.view;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yumly.R;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.IngredientModel;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.core.repo.SearchRepository;
import com.example.yumly.core.models.CountryModel;
import com.example.yumly.databinding.FragmentSearchBinding;
import com.example.yumly.features.search.presenter.SearchPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment implements SearchView, OnItemClickListener {

    FragmentSearchBinding binding;
    ArrayList<CountryModel> countries = new ArrayList<>();
    ArrayList<IngredientModel> ingredients = new ArrayList<>();
    SearchPresenter presenter;
    ArrayList<CatModel> cats;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    Dialog dialog;
    Button cancelBtn;
    Button logoutBtn;


    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.arrowBackId.setOnClickListener(v -> arrowBackOnClick());
        initGridView();
        initPresenter();
        setUpGroupChips();
        handleSearchBTN();
        initDialog();
    }

    void handleSearchBTN() {
        TextInputLayout textInputLayout = binding.searchTextFieldId;
        EditText editText = textInputLayout.getEditText();
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Call filtering directly based on selected chip
                    applyFilterBasedOnChip(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    private void applyFilterBasedOnChip(String query) {
        int checkedChipId = binding.chipGroupFilter.getCheckedChipId();
        if (checkedChipId != View.NO_ID) {
            Chip selectedChip = binding.chipGroupFilter.findViewById(checkedChipId);
            if (selectedChip != null) {
                switch (selectedChip.getText().toString()) {
                    case "Category":
                        filterCategoryName(query);
                        break;
                    case "Ingredient":
                        filterIngredientsName(query);
                        break;
                    case "Country":
                        filterCountryName(query);
                        break;
                }
            }
        }
    }

    private void filterCountryName(String query) {
        List<CountryModel> filteredNames = countries
                .stream()
                .filter(name -> name.getName()
                        .toLowerCase()
                        .startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
        if (filteredNames.isEmpty()) {
            Toast.makeText(getActivity(), "No matches found", Toast.LENGTH_SHORT).show();
        } else {
            GridAdapterChips gridAdapter = new GridAdapterChips(requireContext(), new ArrayList<>(filteredNames), this);
            binding.recyclerViewId.setAdapter(gridAdapter);
        }
    }

    private void filterCategoryName(String query) {
        List<CatModel> filteredNames = cats
                .stream()
                .filter(name -> name.getStrCategory()
                        .toLowerCase()
                        .startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
        if (filteredNames.isEmpty()) {
            Toast.makeText(getActivity(), "No matches found", Toast.LENGTH_SHORT).show();
        } else {
            GridAdapterCategory gridAdapterCategory = new GridAdapterCategory(requireContext(), new ArrayList<>(filteredNames), this);
            binding.recyclerViewId.setAdapter(gridAdapterCategory);
        }
    }

    private void filterIngredientsName(String query) {
        List<IngredientModel> filteredNames = ingredients
                .stream()
                .filter(name -> name.getStrIngredient()
                        .toLowerCase()
                        .startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
        if (filteredNames.isEmpty()) {
            Toast.makeText(getActivity(), "No matches found", Toast.LENGTH_SHORT).show();
        } else {
            GridAdapterIngredients gridAdapterIngredients = new GridAdapterIngredients(requireContext(), new ArrayList<>(filteredNames), this);
            binding.recyclerViewId.setAdapter(gridAdapterIngredients);
        }
    }

    private void initPresenter() {
        presenter = new SearchPresenter(this, SearchRepository.getInstance(MealRemoteDataSource.getInstance()));
        presenter.getData();
        presenter.getCategory();
        presenter.getRemoteIngredients();
    }

    private void arrowBackOnClick() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        navController.navigateUp();
    }

    private void initGridView() {
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 3);
        binding.recyclerViewId.setLayoutManager(layoutManager);
    }

    private void setUpGroupChips() {
        Log.i("TAG", "setUpGroupChips: ++++++" + currentUser);
        binding.chipGroupFilter.setSingleSelection(true);
        for (int i = 0; i < binding.chipGroupFilter.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipGroupFilter.getChildAt(i);
            chip.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    switch (chip.getText().toString()) {
                        case "Category":
                            GridAdapterCategory gridAdapterCategory = new GridAdapterCategory(requireContext(), cats, this);
                            binding.recyclerViewId.setAdapter(gridAdapterCategory);
                            break;
                        case "Ingredient":
                            if (currentUser == null) {
                                dialog.show();
                            } else {
                                GridAdapterIngredients gridAdapterIngredients = new GridAdapterIngredients(requireContext(), ingredients, this);
                                binding.recyclerViewId.setAdapter(gridAdapterIngredients);
                            }
                            break;
                        case "Country":
                            if (currentUser == null) {
                                dialog.show();
                            } else {
                                GridAdapterChips gridAdapter = new GridAdapterChips(requireContext(), countries, this);
                                binding.recyclerViewId.setAdapter(gridAdapter);
                            }
                            break;
                    }
                    Toast.makeText(getActivity(), chip.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void initDialog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_login_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);
        cancelBtn = dialog.findViewById(R.id.dialog_cancel);
        logoutBtn = dialog.findViewById(R.id.dialog_confirm);
        cancelBtn.setOnClickListener(v -> dialog.dismiss());
        logoutBtn.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_searchFragment_to_authMenu);
            dialog.dismiss();
        });
    }

    @Override
    public void getData(ArrayList<CountryModel> countries) {
        this.countries = countries;
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataByCountry(ArrayList<MealModel> countries) {
        MealModel[] mealArray = countries.toArray(new MealModel[0]);
        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void getDataByCategory(ArrayList<MealModel> cat) {
        MealModel[] mealArray = cat.toArray(new MealModel[0]);
        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment action = SearchFragmentDirections
                .actionSearchFragmentToSearchResultFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void getDataByIngredients(ArrayList<MealModel> ingres) {
        MealModel[] mealArray = ingres.toArray(new MealModel[0]);
        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment action = SearchFragmentDirections
                .actionSearchFragmentToSearchResultFragment(mealArray);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void getIngredients(ArrayList<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void getCategory(ArrayList<CatModel> cats) {
        this.cats = cats;
    }

    @Override
    public void onCountryClick(CountryModel countryModel) {
        presenter.getRemoteDataByCountry(countryModel.getName());
    }

    @Override
    public void onCategoryClick(CatModel catModel) {
        presenter.getRemoteDataByCategory(catModel.getStrCategory());
    }

    @Override
    public void onIngredientsClick(IngredientModel ingredientModel) {
        presenter.getRemoteDataByIngredients(ingredientModel.getStrIngredient());
    }
}