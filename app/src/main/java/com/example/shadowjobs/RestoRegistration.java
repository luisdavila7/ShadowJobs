package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shadowjobs.model.restoModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RestoRegistration extends AppCompatActivity {

    Button btnCancel, btnCreate;
    EditText business, email, pass, address, phone, bio;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_registration);

        business = findViewById(R.id.business);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        bio = findViewById(R.id.bio);

        btnCreate = findViewById(R.id.button);
        btnCancel = findViewById(R.id.btnCancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("restaurants");

                UUID uuid = UUID.randomUUID();
                String ID = uuid.toString();

                String restoBusiness = business.getText().toString();
                String restoEmail = email.getText().toString().trim();
                String restoPassword = pass.getText().toString().trim();
                String restoAddress = address.getText().toString();
                String restoPhone = phone.getText().toString();
                String restoBio = bio.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(restoEmail).matches() && restoPassword.length() >= 6){
                    restoModel user = new restoModel(ID,restoBusiness,restoEmail,restoPassword,restoAddress,restoPhone,restoBio);
                    reference.child(ID).setValue(user);
                    Toast.makeText(RestoRegistration.this, "The Restaurant User created successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RestoRegistration.this, LoginActivity.class));
                } else {
                    Toast.makeText(RestoRegistration.this, "Please enter a valid email, and password should be at least 6 characters.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackMenu = new Intent(RestoRegistration.this, LoginActivity.class);
                startActivity(BackMenu);
            }
        });

    }
}