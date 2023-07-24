package com.mthor.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StatsActivity extends AppCompatActivity {

    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        navigation = findViewById(R.id.bottomNavigationView);

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