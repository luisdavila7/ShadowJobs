package com.example.shadowjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterAs extends AppCompatActivity {

    Button btnStudent, btnRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as);

        btnStudent = findViewById(R.id.btnStudent);
        btnRestaurant = findViewById(R.id.btnRestaurant);

        btnStudent.setOnClickListener(v -> startActivity(new Intent(RegisterAs.this, ShadowRegistration.class)));

        btnRestaurant.setOnClickListener(v -> startActivity(new Intent(RegisterAs.this, RestoRegistration.class)));
    }
}