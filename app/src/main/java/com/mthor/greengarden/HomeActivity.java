package com.mthor.greengarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.file.attribute.FileTime;

public class HomeActivity extends AppCompatActivity {

    ImageButton ibCategories;
    ImageButton ibStats;
    ImageButton ibTips;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ibCategories = findViewById(R.id.btCategories);
        ibStats = findViewById(R.id.btStats);
        ibTips = findViewById(R.id.btTips);
        navigation = findViewById(R.id.bottomNavigationView);

        Intent categories = new Intent(this, CategoriesActivity.class);
        Intent stats = new Intent(this, StatsActivity.class);
        Intent tips = new Intent(this, TipsActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        ibCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(categories); }
        });

        ibStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(stats); }
        });

        ibTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(tips); }
        });

        navigation.setOnItemSelectedListener(
                item -> {
                    if (item.getItemId() == R.id.navigation_logout) { startActivity(login); }
                    return true;
                }
        );
    }
}