package com.example.yumly.features.search.view;

import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.CountryModel;

public interface OnItemClickListener {
    void onCountryClick(CountryModel countryModel);
    void onCategoryClick(CatModel catModel);
}
