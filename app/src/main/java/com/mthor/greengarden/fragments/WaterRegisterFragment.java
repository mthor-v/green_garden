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
import com.mthor.greengarden.databinding.FragmentWaterRegisterBinding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WaterRegisterFragment extends Fragment {

    FragmentWaterRegisterBinding binding;
    View rootView;
    ImageButton backButton;
    EditText etLiters, etWaterPrice;
    Spinner spinnerMonths;
    Button btSave;

    public WaterRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWaterRegisterBinding.inflate(inflater,container,false);
        rootView = binding.getRoot();

        backButton = binding.ibBackButtonWaterCons;
        etLiters = binding.etLiters;
        etWaterPrice = binding.etWaterPrice;
        spinnerMonths = binding.spMonths;
        btSave = binding.btSaveWater;
        /*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.months, android.R.layout.simple_dropdown_item_1line);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerMonths.setAdapter(adapter);
        **/
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        backButton.setOnClickListener(view1 -> goToPanel());
        btSave.setOnClickListener(view1 -> saveData());
    }

    private void saveData() {
        Context context = requireContext();
        if (!etLiters.getText().toString().isEmpty() &&
                !etWaterPrice.getText().toString().isEmpty() &&
                !spinnerMonths.getSelectedItem().toString().isEmpty()){
            File file= new File(context.getFilesDir(),"water_records.txt");
            try {
                FileWriter writer= new FileWriter(file,true);
                BufferedWriter bufferedWriter= new BufferedWriter(writer);
                String entry = etLiters.getText().toString().concat(",")
                        .concat(etWaterPrice.getText().toString()).concat(",")
                        .concat(spinnerMonths.getSelectedItem().toString());
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
                bufferedWriter.close();
                Toast.makeText(context, getString(R.string.save_success),
                        Toast.LENGTH_LONG).show();
            }catch (Exception e){
                e.printStackTrace();
            }
            etLiters.setText("");
            etWaterPrice.setText("");
        }else {
            Toast.makeText(context, getString(R.string.empty_fields),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void goToPanel() {
        Navigation.findNavController(rootView).navigate(R.id.action_waterRegisterFragment_to_categoriesPanelFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}