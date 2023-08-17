package com.mthor.greengarden.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.mthor.greengarden.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Button btLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btLogin = binding.btLogin;
        tvRegister = binding.tvRegister;

        Intent home = new Intent(this, HomeActivity.class);
        Intent signup = new Intent(this, RegisterActivity.class);

        btLogin.setOnClickListener(view -> startActivity(home));

        tvRegister.setOnClickListener(view -> startActivity(signup));
    }
}