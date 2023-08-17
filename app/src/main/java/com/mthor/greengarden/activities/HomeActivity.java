package com.mthor.greengarden.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mthor.greengarden.R;
import com.mthor.greengarden.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ImageButton ibCategories;
    ImageButton ibStats;
    ImageButton ibTips;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ibCategories = binding.btCategories;
        ibStats = binding.btStats;
        ibTips = binding.btTips;
        navigation = binding.bottomNavigationView;

        Intent categories = new Intent(this, CategoriesActivity.class);
        Intent stats = new Intent(this, StatsActivity.class);
        Intent tips = new Intent(this, TipsActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        ibCategories.setOnClickListener(view -> startActivity(categories));

        ibStats.setOnClickListener(view -> startActivity(stats));

        ibTips.setOnClickListener(view -> startActivity(tips));

        navigation.setOnItemSelectedListener(
                item -> {
                    if (item.getItemId() == R.id.navigation_logout) { startActivity(login); }
                    return true;
                }
        );
    }
}