package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.shadowjobs.model.restoModel;
import com.example.shadowjobs.model.shadowModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

    public void checkUser(){
        String userEmail = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        DatabaseReference reference;

        if (radioShadow.isChecked()){
            reference = FirebaseDatabase.getInstance().getReference("shadows");
            Query checkUserDatabase = reference.orderByChild("email").equalTo(userEmail);
            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        username.setError(null);
                        for (DataSnapshot ds : snapshot.getChildren()){
                            shadowModel user = ds.getValue(shadowModel.class);
                            if(user.getPassword().equals(userPassword)){
                                Intent intent = new Intent(LoginActivity.this, ShadowProfile.class);
                                intent.putExtra("id", user.getId());
                                intent.putExtra("fName", user.getfName());
                                intent.putExtra("lName", user.getlName());
                                intent.putExtra("phone", user.getPhone());
                                intent.putExtra("email", user.getEmail());
                                intent.putExtra("password", user.getPassword());
                                intent.putExtra("desc", user.getDesc());
                                startActivity(intent);
                                finish();
                            } else {
                                username.setError("Email or password invalid");
                                username.requestFocus();
                            }
                        }
                    } else {
                        username.setError("Email or password invalid");
                        username.requestFocus();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        } /*else if (radioRestaurant.isChecked()){
            reference = FirebaseDatabase.getInstance().getReference("restaurants");
            reference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        restoModel user = ds.getValue(restoModel.class);
                        if (user.getEmail().equals(userUserName) && user.getPassword().equals(userPassword)) {
                            Intent intent = new Intent(LoginActivity.this, RestoProfile.class);
                            intent.putExtra("id", user.getId());
                            intent.putExtra("business", user.getBusiness());
                            intent.putExtra("address", user.getAddress());
                            intent.putExtra("phone", user.getPhone());
                            intent.putExtra("email", user.getEmail());
                            intent.putExtra("password", user.getPassword());
                            intent.putExtra("bio", user.getBio());
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                //Toast.makeText(LoginActivity.this, "Email or Password Invalid", Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(LoginActivity.this, "Please select a type of user", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}



