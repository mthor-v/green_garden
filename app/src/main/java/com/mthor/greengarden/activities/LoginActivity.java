package com.mthor.greengarden.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mthor.greengarden.R;
import com.mthor.greengarden.databinding.ActivityLoginBinding;
import com.mthor.greengarden.models.CredentialsDTO;
import com.mthor.greengarden.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    TextView email, password;
    Button btLogin;
    TextView tvRegister;
    File usersFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        email = binding.etEmailLogin;
        password = binding.etPasswordLogin;
        btLogin = binding.btLogin;
        tvRegister = binding.tvRegister;

        usersFile = new File(getFilesDir(),"users_list.txt");

        Intent home = new Intent(this, HomeActivity.class);
        Intent signup = new Intent(this, RegisterActivity.class);

        btLogin.setOnClickListener(view -> {
            if(checkFields()) {
                CredentialsDTO credentials = new CredentialsDTO(
                        email.getText().toString(), password.getText().toString());
                if (checkUser(usersFile, credentials)) startActivity(home);
                else {
                    Toast.makeText(this, getString(R.string.wrong_user), Toast.LENGTH_LONG).show();
                    formWipe();
                }
            }else Toast.makeText(
                    this,getString(R.string.empty_fields),Toast.LENGTH_LONG).show();
        });

        tvRegister.setOnClickListener(view -> startActivity(signup));
    }

    public boolean checkFields () {
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

    public boolean checkUser (File file, CredentialsDTO credentials) {
        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int recordsFound = 0;
                User user = null;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String email = data[1];
                    if(email.equals(credentials.getEmail())) {
                        user = new User(data[0], data[1], data[2], data[3]);
                        recordsFound++;
                    }
                }
                if(recordsFound > 0){
                    return user.getPassword().equals(credentials.getPassword());
                }else return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public void formWipe(){
        email.setText("");
        password.setText("");
    }
}