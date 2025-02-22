package com.example.yumly.features.details.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yumly.R;
import com.example.yumly.core.local.MealsLocalDataSource;
import com.example.yumly.core.models.IngredientDetailsModel;
import com.example.yumly.core.models.MealModel;
import com.example.yumly.core.models.PlanModel;
import com.example.yumly.core.models.UserModel;
import com.example.yumly.core.remote.MealRemoteDataSource;
import com.example.yumly.core.repo.MealsRepository;
import com.example.yumly.databinding.FragmentDetailsBinding;
import com.example.yumly.features.auth.views.AuthMenuViewDirections;
import com.example.yumly.features.details.presenter.DetailsPresenter;
import com.example.yumly.features.search.view.SearchResultFragmentArgs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetailsFragment extends Fragment implements DetailsView {

    FragmentDetailsBinding binding;
    MealModel mealModel;
    ArrayList<IngredientDetailsModel> ingredientDetails;
    DetailsPresenter presenter;
    ArrayList<MealModel> mealDB = new ArrayList<>();
    boolean isFav;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public DetailsFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
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
        initPresenter();
        initYoutube();
    }

    void initPresenter() {
        presenter = new DetailsPresenter(this,
                MealsRepository
                        .getInstance(MealsLocalDataSource
                                        .getInstance(getContext()),
                                MealRemoteDataSource
                                        .getInstance()));
        presenter.getMealFromDatabase();
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
        binding.favBtnId.setOnClickListener(v -> {
            if (isFav) {
                isFav = false;
                binding.favIconId.setImageResource(R.drawable.favorite);
                presenter.removeFromFav(mealModel);
            } else {
                isFav = true;
                binding.favIconId.setImageResource(R.drawable.solid_favorite);
                presenter.addToFav(mealModel);
            }
        });
        binding.btnAddToCalendarId.setOnClickListener(v -> {
            Calender.showDate(requireContext(), selectedDate -> {
                presenter.insertToPlane(new PlanModel(selectedDate, currentUser.getUid(), mealModel));
            });
        });
    }

    void initIsFav() {
        isFav = false;
        for (MealModel model : mealDB) {
            if (model.getIdMeal().equals(mealModel.getIdMeal())) {
                binding.favIconId.setImageResource(R.drawable.solid_favorite);
                isFav = true;
                break;
            }
        }
        if (!isFav) {
            binding.favIconId.setImageResource(R.drawable.favorite);
        }
    }


    void initYoutube() {
        YouTubePlayerView youTubePlayerView = binding.youtubePlayerView;
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = getVideoKey();
                if (videoId != null && !videoId.isEmpty()) {
                    youTubePlayer.cueVideo(videoId, 0);
                }
            }
        });
    }

    String getVideoKey() {
        String url = mealModel.getStrYoutube();
        if (url == null || url.isEmpty()) {
            return null; // or provide a default value
        }

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

        String query = uri.getQuery();
        if (query == null) {
            return null;
        }

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

    @Override
    public void onSuccessAddToFav(MealModel mealModel) {
        Toast.makeText(getContext(), "Add " + mealModel.getStrMeal() + " to Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRemoveFromFav(MealModel mealModel) {
        Toast.makeText(getContext(), "Remove " + mealModel.getStrMeal() + " from Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessGetMealFromDB(ArrayList<MealModel> meals) {
        this.mealDB = meals;
        initIsFav();
    }

    @Override
    public void onSuccessAddToPlan(PlanModel planModel) {
        Toast.makeText(requireContext(), "Added " +planModel.getMeal().getStrMeal() + " successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onError: " + msg);
    }
}