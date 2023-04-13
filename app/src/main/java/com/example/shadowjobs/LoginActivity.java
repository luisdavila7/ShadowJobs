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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUserDatabase;

        if (radioShadow.isChecked()){
            checkUserDatabase = reference.orderByChild("shadows").equalTo(userUserName);
        }else{
            checkUserDatabase = reference.orderByChild("restaurants").equalTo(userUserName);
        }
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    username.setError(null);
                    String passformData = snapshot.child(userUserName).child("password").getValue(String.class);

                    if (passformData.equals(userPassword)){

                        //
                        Toast.makeText(LoginActivity.this, "happy", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,ShadowProfile.class));
                    }
                    else{
                        password.setError("Invalid credentinal");
                        password.requestFocus();
                    }


                }
                else{
                    password.setError("Invalid credentinal");
                    password.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

//    public void checkUser(){
//        String userUserName = username.getText().toString().trim();
//        String userPassword = password.getText().toString().trim();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUserName);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    username.setError(null);
//                    String passformData = snapshot.child(userUserName).child("password").getValue(String.class);
//                    if (passformData.equals(userPassword)){
//                        String usernamedb = snapshot.child(userUserName).child("username").getValue(String.class);
//                        String nameDB = snapshot.child(userUserName).child("name").getValue(String.class);
//                        String emailDb = snapshot.child(userUserName).child("email").getValue(String.class);
//                        String passdb = snapshot.child(userUserName).child("password").getValue(String.class);
//
//                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                        intent.putExtra("name",nameDB);
//                        intent.putExtra("email",emailDb);
//                        intent.putExtra("username",usernamedb);
//                        intent.putExtra("password",passdb);
//
//                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//                    }
//                    else{
//                        password.setError("Invalid credentinal");
//                        password.requestFocus();
//                    }
//
//
//                }
//                else{
//                    password.setError("Invalid credentinal");
//                    password.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

