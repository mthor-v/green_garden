package com.mthor.greengarden.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mthor.greengarden.R;
import com.mthor.greengarden.models.Water;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.mthor.greengarden.databinding.ActivityStatsBinding;

public class StatsActivity extends AppCompatActivity {

    ActivityStatsBinding binding;
    TableLayout waterTable;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        waterTable = binding.tlWaterStatsContent;
        navigation = binding.bottomNavigationView;

        Intent home = new Intent(this, HomeActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        File waterFile= new File(getFilesDir(), "water_records.txt");

        List<Water> waterList= readFileWater(waterFile);
        addWaterData(waterList);

        navigation.setOnItemSelectedListener(
                item -> {
                    if (item.getItemId() == R.id.navigation_home) {
                        startActivity(home);
                    }
                    if (item.getItemId() == R.id.navigation_logout) {
                        startActivity(login);
                    }
                    return true;
                }
        );
    }

    public List<Water> readFileWater(File file) {
        List<Water> waterList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                float liters = Float.parseFloat(data[0]);
                float price = Float.parseFloat(data[1]);
                String month = data[2];
                Water waterObj = new Water(liters, price, month);
                waterList.add(waterObj);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waterList;
    }

    public void addWaterData(List<Water> waterList) {

        if (!waterList.isEmpty()) {
            int records = 0;
            float liters = 0;
            float prices = 0;
            for (Water w : waterList) {
                TableRow row = new TableRow(this);

                TextView field1 = new TextView(this);
                field1.setText(w.getMonth());
                field1.setTextAppearance(R.style.simple_table_field);
                field1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                field1.setWidth(117);
                row.addView(field1);

                TextView field2 = new TextView(this);
                field2.setText(String.valueOf(w.getLiters()).concat(" Lt."));
                field2.setTextAppearance(R.style.simple_table_field);
                field2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                liters += w.getLiters();
                row.addView(field2);

                TextView field3 = new TextView(this);
                field3.setText("$ ".concat(String.valueOf(w.getPrice())));
                field3.setTextAppearance(R.style.simple_table_field);
                field3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                prices += w.getPrice();
                row.addView(field3);

                waterTable.addView(row);
                records ++;
            }
            TableRow avgs = new TableRow(this);

            TextView avgName = new TextView(this);
            avgName.setText(R.string.average);
            avgName.setTextAppearance(R.style.title_table);
            avgName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgName.setTextColor(ContextCompat.getColor(this, R.color.app_purple_2));
            avgs.addView(avgName);

            TextView avgLiters = new TextView(this);
            avgLiters.setText(String.valueOf(Math.round((liters/records)*100.0)/100.0).concat(" Lt."));
            avgLiters.setTextAppearance(androidx.appcompat.R.style.Base_Widget_AppCompat_EditText);
            avgLiters.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgs.addView(avgLiters);

            TextView avgPrices = new TextView(this);
            avgPrices.setText("$ ".concat(String.valueOf(Math.round((prices/records)*100.0)/100.0)));
            avgPrices.setTextAppearance(androidx.appcompat.R.style.Base_Widget_AppCompat_EditText);
            avgPrices.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgs.addView(avgPrices);

            waterTable.addView(avgs);
        } else {
            TableRow emptyRow = new TableRow(this);
            TextView emptyField = new TextView(this);
            emptyField.setText(R.string.empty_message);
            emptyRow.addView(emptyField);
            waterTable.addView(emptyRow);
        }
    }
}