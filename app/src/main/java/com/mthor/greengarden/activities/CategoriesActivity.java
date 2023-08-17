package com.mthor.greengarden.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mthor.greengarden.R;

import com.mthor.greengarden.databinding.ActivityCategoriesBinding;

public class CategoriesActivity extends AppCompatActivity {

    ActivityCategoriesBinding binding;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigation = binding.bottomNavigationView;

        Intent home = new Intent(this, HomeActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        navigation.setOnItemSelectedListener(
                item -> {
                    if (item.getItemId() == R.id.navigation_home) { startActivity(home); }
                    if (item.getItemId() == R.id.navigation_logout) { startActivity(login); }
                    return true;
                }
        );
    }
}