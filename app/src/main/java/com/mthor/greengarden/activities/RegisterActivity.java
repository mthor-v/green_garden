package com.mthor.greengarden.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mthor.greengarden.R;
import com.mthor.greengarden.databinding.ActivityRegisterBinding;
import com.mthor.greengarden.models.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    EditText fullName, email, city, password;
    Button btRegister;
    TextView tvHaveAccount;
    File usersFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fullName = binding.etFullName;
        email = binding.etEmailSignup;
        city = binding.etCity;
        password = binding.etPasswordSignup;
        btRegister = binding.btSignup;
        tvHaveAccount = binding.tvAccountExists;

        usersFile = new File(getFilesDir(),"users_list.txt");

        Intent home = new Intent(this, HomeActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        btRegister.setOnClickListener(view -> {
            User user = new User(
                    fullName.getText().toString(),
                    email.getText().toString(),
                    city.getText().toString(),
                    password.getText().toString());
            if(!checkUser(usersFile, email.getText().toString())){
                if(saveData(user)) startActivity(home);
            }else{
                Toast.makeText(this,getString(R.string.user_exists),Toast.LENGTH_LONG).show();
                formWipe();
            }
        });

        tvHaveAccount.setOnClickListener(view -> startActivity(login));
    }

    public boolean checkUser (File file, String emailEntered) {
        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int recordsFound = 0;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String email = data[1];
                    if(email.equals(emailEntered)) recordsFound ++;
                }
                return recordsFound > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean saveData(User user) {
        if (!fullName.getText().toString().isEmpty() &&
                !email.getText().toString().isEmpty() &&
                !city.getText().toString().isEmpty() &&
                !password.getText().toString().isEmpty()){
            try {
                FileWriter writer= new FileWriter(usersFile,true);
                BufferedWriter bufferedWriter= new BufferedWriter(writer);
                String entry = user.getFullName().concat(",")
                        .concat(user.getEmail()).concat(",")
                        .concat(user.getCity()).concat(",")
                        .concat(user.getPassword());
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
                bufferedWriter.close();
                Toast.makeText(this, getString(R.string.user_done),
                        Toast.LENGTH_LONG).show();
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }else {
            Toast.makeText(this, getString(R.string.empty_fields),
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void formWipe(){
        fullName.setText("");
        email.setText("");
        city.setText("");
        password.setText("");
    }

}