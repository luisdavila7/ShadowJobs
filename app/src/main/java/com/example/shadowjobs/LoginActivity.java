package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.shadowjobs.model.restoModel;
import com.example.shadowjobs.model.shadowModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp, btnForgotPass;
    EditText username, password;
    RadioButton radioShadow, radioRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.signup);
        btnForgotPass = findViewById(R.id.forgotPass);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        radioShadow = findViewById(R.id.rBtnShadows);
        radioRestaurant = findViewById(R.id.rBtnRestaurants);

        btnLogin.setOnClickListener(view -> checkUser());

        btnSignUp.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterAs.class)));
    }


    public Boolean validUserName() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Username is empty");
            return false;
        } else {
            return true;
        }
    }

    public Boolean validPass() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("password is empty");
            return false;
        } else {
            return true;
        }
    }

    public void checkUser(){
        String userUserName = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        DatabaseReference reference;

        if (radioShadow.isChecked()){
            reference = FirebaseDatabase.getInstance().getReference("shadows");
            reference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        shadowModel user = ds.getValue(shadowModel.class);
                        if (user.getEmail().equals(userUserName) && user.getPassword().equals(userPassword)){
                            Toast.makeText(LoginActivity.this, userUserName+" "+userPassword, Toast.LENGTH_SHORT).show();
                            Intent intentShadow = new Intent(LoginActivity.this, ShadowProfile.class);

                            intentShadow.putExtra("id", user.getId());
                            intentShadow.putExtra("fName", user.getfName());
                            intentShadow.putExtra("lName", user.getlName());
                            intentShadow.putExtra("phone", user.getPhone());
                            intentShadow.putExtra("email", user.getEmail());
                            intentShadow.putExtra("password", user.getPassword());
                            intentShadow.putExtra("desc", user.getDesc());

                            Toast.makeText(LoginActivity.this, "Im here Shadow Login", Toast.LENGTH_SHORT).show();

                            startActivity(intentShadow);
                        }
                    }
                }

            });
        } else if (radioRestaurant.isChecked()){
            reference = FirebaseDatabase.getInstance().getReference("restaurants");
            reference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        restoModel user = ds.getValue(restoModel.class);
                        if (user.getEmail().equals(userUserName) && user.getPassword().equals(userPassword)) {
                            Toast.makeText(LoginActivity.this, userUserName, Toast.LENGTH_SHORT).show();

                            Intent intentResto = new Intent(LoginActivity.this, RestoProfile.class);
                            intentResto.putExtra("id", user.getId());
                            intentResto.putExtra("name", user.getName());
                            intentResto.putExtra("address", user.getAddress());
                            intentResto.putExtra("phone", user.getPhone());
                            intentResto.putExtra("email", user.getEmail());
                            intentResto.putExtra("password", user.getPassword());
                            intentResto.putExtra("bio", user.getBio());

                            Toast.makeText(LoginActivity.this, "Im here Restaurant Login", Toast.LENGTH_SHORT).show();
                            startActivity(intentResto);
                        }
                    }
                }
            });

        } else{
            Toast.makeText(LoginActivity.this, "The User and Password are incorrect.", Toast.LENGTH_SHORT).show();
        }
    }
}



