package com.mthor.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoriesActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    ImageButton btCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        navigation = findViewById(R.id.bottomNavigationView);
        btCategories = findViewById(R.id.ibWaterCat);

        Intent home = new Intent(this, HomeActivity.class);
        Intent login = new Intent(this, LoginActivity.class);
        Intent waterRegister = new Intent(this, WaterRegisterActivity.class);

        btCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(waterRegister); }
        });

        navigation.setOnItemSelectedListener(
                item -> {
                    if (item.getItemId() == R.id.navigation_home) { startActivity(home); }
                    if (item.getItemId() == R.id.navigation_logout) { startActivity(login); }
                    return true;
                }
        );
    }
}