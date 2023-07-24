package com.mthor.greengarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WaterRegisterActivity extends AppCompatActivity {

    ImageButton backButton;
    EditText etLiters, etWaterPrice;
    Button btSave;
    Spinner spinnerMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_register);

        backButton = findViewById(R.id.ibBackButtonWaterCons);
        etLiters = findViewById(R.id.etLiters);
        etWaterPrice = findViewById(R.id.etWaterPrice);
        spinnerMonths = findViewById(R.id.spMonths);
        btSave = findViewById(R.id.btSaveWater);

        Intent categories = new Intent(this, CategoriesActivity.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startActivity(categories); }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etLiters.getText().toString().isEmpty() &&
                        !etWaterPrice.getText().toString().isEmpty() &&
                        !spinnerMonths.getSelectedItem().toString().isEmpty()){
                    File file= new File(getFilesDir(),"water_records.txt");
                    try {
                        FileWriter writer= new FileWriter(file,true);
                        BufferedWriter bufferedWriter= new BufferedWriter(writer);
                        String entry = "Se registran ".concat(etLiters.getText().toString())
                                .concat(" con valor de $")
                                .concat(etWaterPrice.getText().toString())
                                .concat(" en el mes de ")
                                .concat(spinnerMonths.getSelectedItem().toString());
                        bufferedWriter.write(entry);
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                        Toast.makeText(WaterRegisterActivity.this, "¡Información almacenada con exito!",
                                Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    etLiters.setText("");
                    etWaterPrice.setText("");
                }else {
                    Toast.makeText(WaterRegisterActivity.this, "¡Todos los campos deben estar diligenciados!",
                            Toast.LENGTH_LONG).show();
                };
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.months, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonths.setAdapter(adapter);
    }
}