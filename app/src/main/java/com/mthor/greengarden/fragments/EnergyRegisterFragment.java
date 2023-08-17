package com.mthor.greengarden.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.mthor.greengarden.R;
import com.mthor.greengarden.databinding.FragmentEnergyRegisterBinding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class EnergyRegisterFragment extends Fragment {

    FragmentEnergyRegisterBinding binding;
    View rootView;
    ImageButton backButton;
    EditText etKiloWats, etKiloWatsPrice;
    Spinner spinnerMonths;
    Button btSave;

    public EnergyRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEnergyRegisterBinding.inflate(inflater,container,false);
        rootView = binding.getRoot();

        backButton = binding.ibBackButtonEnergyCons;
        etKiloWats = binding.etKwats;
        etKiloWatsPrice = binding.etEnergyPrice;
        spinnerMonths = binding.spMonths;
        btSave = binding.btSaveEnergy;

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        backButton.setOnClickListener(view1 -> goToPanel());
        btSave.setOnClickListener(view1 -> saveData());
    }

    private void saveData() {
        Context context = requireContext();
        if (!etKiloWats.getText().toString().isEmpty() &&
                !etKiloWatsPrice.getText().toString().isEmpty() &&
                !spinnerMonths.getSelectedItem().toString().isEmpty()){
            File file= new File(context.getFilesDir(),"energy_records.txt");
            try {
                FileWriter writer= new FileWriter(file,true);
                BufferedWriter bufferedWriter= new BufferedWriter(writer);
                String entry = etKiloWats.getText().toString().concat(",")
                        .concat(etKiloWatsPrice.getText().toString()).concat(",")
                        .concat(spinnerMonths.getSelectedItem().toString());
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
                bufferedWriter.close();
                Toast.makeText(context, getString(R.string.save_success),
                        Toast.LENGTH_LONG).show();
            }catch (Exception e){
                e.printStackTrace();
            }
            etKiloWats.setText("");
            etKiloWatsPrice.setText("");
        }else {
            Toast.makeText(context, getString(R.string.empty_fields),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void goToPanel() {
        Navigation.findNavController(rootView).navigate(R.id.action_energyRegisterFragment_to_categoriesPanelFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}