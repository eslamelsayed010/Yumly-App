package com.example.yumly;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment_container);

        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.loginView) {
                bottomNavigationView.setVisibility(View.INVISIBLE);
            } else if (navDestination.getId() == R.id.signupView) {
                bottomNavigationView.setVisibility(View.INVISIBLE);
            } else if (navDestination.getId() == R.id.authMenu) {
                bottomNavigationView.setVisibility(View.INVISIBLE);
            } else if (navDestination.getId() == R.id.splashView) {
                bottomNavigationView.setVisibility(View.INVISIBLE);
            } else if (navDestination.getId() == R.id.searchFragment) {
                bottomNavigationView.setVisibility(View.INVISIBLE);
            } else if (navDestination.getId() == R.id.detailsFragment) {
                bottomNavigationView.setVisibility(View.INVISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
    }

}