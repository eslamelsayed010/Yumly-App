package com.example.yumly.features.details.view;

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
import android.webkit.WebChromeClient;

import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.models.IngredientDetailsModel;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.databinding.FragmentDetailsBinding;
import com.example.yumly.databinding.FragmentHomeViewBinding;
import com.example.yumly.features.search.view.SearchResultFragmentArgs;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetailsFragment extends Fragment {

    FragmentDetailsBinding binding;
    MealModel mealModel;
    ArrayList<IngredientDetailsModel> ingredientDetails;

    public DetailsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMeal();
        initItem();
        initRecycleView();
        initYoutube();
    }

    void initMeal() {
        mealModel = new ArrayList<>(Arrays
                .asList(SearchResultFragmentArgs
                        .fromBundle(getArguments())
                        .getMeals())).get(0);
        ingredientDetails = new ArrayList<>(mealModel.getIngredientList());
    }

    void initItem() {
        binding.arrowBackId.setOnClickListener(v -> arrowBackOnClick());
        binding.catMealDetailsId.setText(mealModel.getStrCategory());
        binding.titleMealDetailsId.setText(mealModel.getStrMeal());
        binding.countryMealDetailsId.setText(mealModel.getStrArea());
        binding.descMealDetailsId.setText(mealModel.getStrInstructions());
        Glide.with(getContext()).load(mealModel.getStrMealThumb()).into(binding.imageViewDetailsId);
    }

    void initYoutube() {
        YouTubePlayerView youTubePlayerView = binding.youtubePlayerView;
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = getVideoKey();
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }

    String getVideoKey() {
        String url = mealModel.getStrYoutube();
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String query = uri.getQuery();

        Map<String, String> queryPairs = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2) {
                queryPairs.put(pair[0], pair[1]);
            }
        }
        return queryPairs.get("v");
    }

    void initRecycleView() {
        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapterDetails myAdapter = new MyAdapterDetails(getContext(), ingredientDetails);
        binding.recyclerView.setAdapter(myAdapter);
    }

    private void arrowBackOnClick() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        navController.navigateUp();
    }
}