package com.mthor.greengarden.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mthor.greengarden.R;
import com.mthor.greengarden.models.Energy;
import com.mthor.greengarden.models.Fertilizer;
import com.mthor.greengarden.models.Water;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mthor.greengarden.databinding.ActivityStatsBinding;

public class StatsActivity extends AppCompatActivity {

    ActivityStatsBinding binding;
    TableLayout waterTable, fertilizerTable, energyTable;
    BottomNavigationView navigation;
    Button wipeWater, wipeFertilizer, wipeEnergy;
    TextView log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        waterTable = binding.tlWaterStatsContent;
        fertilizerTable = binding.tlFertilizerStatsContent;
        energyTable = binding.tlEnergyStatsContent;
        navigation = binding.bottomNavigationView;
        wipeWater = binding.btWipeWater;
        wipeFertilizer = binding.btWipeFertilizer;
        wipeEnergy = binding.btWipeEnergy;
        log = binding.errorLog;

        Intent home = new Intent(this, HomeActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        File waterFile = new File(getFilesDir(), "water_records.txt");
        File fertilizerFile = new File(getFilesDir(), "fertilizer_records.txt");
        File energyFile = new File(getFilesDir(), "energy_records.txt");

        List<Water> waterList = readFileWater(waterFile);
        addWaterData(waterList);

        List<Fertilizer> fertilizerList = readFileFertilizer(fertilizerFile);
        addFertilizerData(fertilizerList);

        List<Energy> energyList = readFileEnergy(energyFile);
        addEnergyData(energyList);

        wipeWater.setOnClickListener(view -> clearFileContent(waterFile));
        wipeFertilizer.setOnClickListener(view -> clearFileContent(fertilizerFile));
        wipeEnergy.setOnClickListener(view -> clearFileContent(energyFile));

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
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                float liters = Float.parseFloat(data[0]);
                float price = Float.parseFloat(data[1]);
                String month = data[2];
                Water waterObj = new Water(liters, price, month);
                waterList.add(waterObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waterList;
    }

    public List<Fertilizer> readFileFertilizer(File file) {
        List<Fertilizer> fertilizerList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                float kilos = Float.parseFloat(data[0]);
                float price = Float.parseFloat(data[1]);
                String month = data[2];
                Fertilizer fertilizerObj = new Fertilizer(kilos, price, month);
                fertilizerList.add(fertilizerObj);
            }
            return fertilizerList;
        } catch (Exception e) {
            e.printStackTrace();
            return fertilizerList;
        }
    }

    public List<Energy> readFileEnergy(File file) {
        List<Energy> energyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                float kiloWats = Float.parseFloat(data[0]);
                float price = Float.parseFloat(data[1]);
                String month = data[2];
                Energy energyObj = new Energy(kiloWats, price, month);
                energyList.add(energyObj);
            }
            return energyList;
        } catch (Exception e) {
            e.printStackTrace();
            return energyList;
        }
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
            TextView emptyField = new TextView(this);
            emptyField.setText(R.string.empty_message);
            emptyField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            emptyField.setPadding(0,5,0,5);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            emptyField.setLayoutParams(layoutParams);
            waterTable.addView(emptyField);
        }
    }

    public void addFertilizerData(List<Fertilizer> fertilizerList) {

        if (!fertilizerList.isEmpty()) {
            int records = 0;
            float kilos = 0;
            float prices = 0;
            for (Fertilizer f : fertilizerList) {
                TableRow row = new TableRow(this);

                TextView field1 = new TextView(this);
                field1.setText(f.getMonth());
                field1.setTextAppearance(R.style.simple_table_field);
                field1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                field1.setWidth(117);
                row.addView(field1);

                TextView field2 = new TextView(this);
                field2.setText(String.valueOf(f.getKilos()).concat(" Kg."));
                field2.setTextAppearance(R.style.simple_table_field);
                field2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                kilos += f.getKilos();
                row.addView(field2);

                TextView field3 = new TextView(this);
                field3.setText("$ ".concat(String.valueOf(f.getPrice())));
                field3.setTextAppearance(R.style.simple_table_field);
                field3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                prices += f.getPrice();
                row.addView(field3);

                fertilizerTable.addView(row);
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
            avgLiters.setText(String.valueOf(Math.round((kilos/records)*100.0)/100.0).concat(" Kg."));
            avgLiters.setTextAppearance(androidx.appcompat.R.style.Base_Widget_AppCompat_EditText);
            avgLiters.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgs.addView(avgLiters);

            TextView avgPrices = new TextView(this);
            avgPrices.setText("$ ".concat(String.valueOf(Math.round((prices/records)*100.0)/100.0)));
            avgPrices.setTextAppearance(androidx.appcompat.R.style.Base_Widget_AppCompat_EditText);
            avgPrices.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgs.addView(avgPrices);

            fertilizerTable.addView(avgs);
        } else {
            TextView emptyField = new TextView(this);
            emptyField.setText(R.string.empty_message);
            emptyField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            emptyField.setPadding(0,5,0,5);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            );
            emptyField.setLayoutParams(layoutParams);
            fertilizerTable.addView(emptyField);
        }
    }

    public void addEnergyData(List<Energy> energyList) {

        if (!energyList.isEmpty()) {
            int records = 0;
            float kiloWats = 0;
            float prices = 0;
            for (Energy e : energyList) {
                TableRow row = new TableRow(this);

                TextView field1 = new TextView(this);
                field1.setText(e.getMonth());
                field1.setTextAppearance(R.style.simple_table_field);
                field1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                field1.setWidth(117);
                row.addView(field1);

                TextView field2 = new TextView(this);
                field2.setText(String.valueOf(e.getKiloWats()).concat(" Kw."));
                field2.setTextAppearance(R.style.simple_table_field);
                field2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                kiloWats += e.getKiloWats();
                row.addView(field2);

                TextView field3 = new TextView(this);
                field3.setText("$ ".concat(String.valueOf(e.getPrice())));
                field3.setTextAppearance(R.style.simple_table_field);
                field3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                prices += e.getPrice();
                row.addView(field3);

                energyTable.addView(row);
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
            avgLiters.setText(String.valueOf(Math.round((kiloWats/records)*100.0)/100.0).concat(" Kw."));
            avgLiters.setTextAppearance(androidx.appcompat.R.style.Base_Widget_AppCompat_EditText);
            avgLiters.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgs.addView(avgLiters);

            TextView avgPrices = new TextView(this);
            avgPrices.setText("$ ".concat(String.valueOf(Math.round((prices/records)*100.0)/100.0)));
            avgPrices.setTextAppearance(androidx.appcompat.R.style.Base_Widget_AppCompat_EditText);
            avgPrices.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            avgs.addView(avgPrices);

            energyTable.addView(avgs);
        } else {
            TextView emptyField = new TextView(this);
            emptyField.setText(R.string.empty_message);
            emptyField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            emptyField.setPadding(0,5,0,5);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            emptyField.setLayoutParams(layoutParams);
            energyTable.addView(emptyField);
        }
    }

    public void clearFileContent(File file) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert_subtitle))
        .setMessage(getString(R.string.alert_message))
        .setPositiveButton("Sí", (dialog, which) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
                writer.write("");
                writer.close();
                recreate();
            }  catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        })
        .setNegativeButton("No", (dialog, which) -> {
        })
        .show();
    }
}