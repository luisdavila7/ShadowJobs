package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shadowjobs.model.restoModel;
import com.example.shadowjobs.model.shadowModel;
import com.example.shadowjobs.model.restoModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RestoRegistration extends AppCompatActivity {

    Button btnCancel, btnCreate;
    EditText business, email, pass, address, phone;
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

        btnCreate = findViewById(R.id.button);
        btnCancel = findViewById(R.id.btnCancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("restaurants");

                String busi_ness = business.getText().toString();
                String e_mail = email.getText().toString();
                String password = pass.getText().toString();
                String add_ress = address.getText().toString();
                String pho_ne = phone.getText().toString();

                restoModel user = new restoModel(busi_ness,e_mail,password,add_ress,pho_ne);

                reference.child(e_mail).setValue(user);

                Toast.makeText(RestoRegistration.this, "The Restaurant User created successfully.", Toast.LENGTH_SHORT).show();
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