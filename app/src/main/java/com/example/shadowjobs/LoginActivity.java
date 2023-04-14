package com.example.shadowjobs;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shadowjobs.model.Job;
import com.example.shadowjobs.model.restoModel;
import com.example.shadowjobs.model.shadowModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.signup);
        btnForgotPass = findViewById(R.id.forgotPass);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        radioShadow = findViewById(R.id.rBtnShadows);
        radioRestaurant = findViewById(R.id.rBtnRestaurants);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });

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

        Query checkUserDatabase;

        if (radioShadow.isChecked()){
            reference = FirebaseDatabase.getInstance().getReference("shadows");
            reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DataSnapshot ds : task.getResult().getChildren()){
                            shadowModel user = ds.getValue(shadowModel.class);
                            if (user.getEmail().equals(userUserName) && user.getPassword().equals(userPassword)){
                                Toast.makeText(LoginActivity.this, userUserName+" "+userPassword, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, ShadowProfile.class);
                                intent.putExtra("id", user.getId());
                                intent.putExtra("fName", user.getfName());
                                intent.putExtra("lName", user.getlName());
                                intent.putExtra("phone", user.getPhone());
                                intent.putExtra("email", user.getEmail());
                                intent.putExtra("password", user.getPassword());
                                intent.putExtra("address", user.getAddress());
                                startActivity(intent);
                            }
                        }
                        //Toast.makeText(LoginActivity.this, "The User and Password are incorrect.1", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "The User and Password are incorrect.2", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            reference = FirebaseDatabase.getInstance().getReference("restaurants");
            reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DataSnapshot ds : task.getResult().getChildren()) {

                            restoModel user = ds.getValue(restoModel.class);
                            //Toast.makeText(LoginActivity.this, user.getEmail() + user.getPassword(), Toast.LENGTH_SHORT).show();
                            if (user.getEmail().equals(userUserName) && user.getPassword().equals(userPassword)) {
                                Toast.makeText(LoginActivity.this, userUserName, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, RestoProfile.class));
                            }
                        }
                    }
                }
            });
        }

    }
}



