package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShadowRegistration extends AppCompatActivity {

    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_registration);


        cancel = findViewById(R.id.btnCancel);

        cancel.setOnClickListener(v -> {
            Intent BackMenu = new Intent(ShadowRegistration.this, LoginActivity.class);
            startActivity(BackMenu);
        });
    }
}