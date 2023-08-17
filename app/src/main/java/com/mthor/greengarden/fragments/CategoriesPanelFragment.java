package com.mthor.greengarden.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mthor.greengarden.R;
import com.mthor.greengarden.databinding.FragmentCategoriesPanelBinding;

public class CategoriesPanelFragment extends Fragment {

    FragmentCategoriesPanelBinding binding;
    View rootView;
    ImageButton btWater, btFertilizer, btEnergy;

    public CategoriesPanelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoriesPanelBinding.inflate(inflater,container,false);
        rootView = binding.getRoot();
        btWater = binding.ibWaterCat;
        btFertilizer = binding.ibFertilizerCat;
        btEnergy = binding.ibEnergyCat;

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        btWater.setOnClickListener(view1 -> goToWaterRegister());
        btFertilizer.setOnClickListener(view1 -> goToFertilizerRegister());
        btEnergy.setOnClickListener(view1 -> goToEnergyRegister());
    }

    private void goToWaterRegister() {
        Navigation.findNavController(rootView).navigate(R.id.action_categoriesPanelFragment_to_waterRegisterFragment);
    }

    private void goToFertilizerRegister() {
        Navigation.findNavController(rootView).navigate(R.id.action_categoriesPanelFragment_to_fertilizerRegisterFragment);
    }

    private void goToEnergyRegister() {
        Navigation.findNavController(rootView).navigate(R.id.action_categoriesPanelFragment_to_energyRegisterFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}