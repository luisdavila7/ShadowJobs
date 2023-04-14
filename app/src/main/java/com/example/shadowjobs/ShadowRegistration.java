package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shadowjobs.model.shadowModel;
import com.example.shadowjobs.model.shadowModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class ShadowRegistration extends AppCompatActivity {

    Button btnCancel, btnCreate;
    EditText fname, lname, email, pass, address, phone;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_registration);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
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
                reference = database.getReference("shadows");

                UUID uuid = UUID.randomUUID();
                String ID = uuid.toString();

                String f_name = fname.getText().toString();
                String l_name = lname.getText().toString();
                String e_mail = email.getText().toString();
                String password = pass.getText().toString();
                String add_ress = address.getText().toString();
                String pho_ne = phone.getText().toString();

                shadowModel user = new shadowModel(ID,f_name,l_name,e_mail,password,add_ress,pho_ne);

                reference.child(ID).setValue(user);

                Toast.makeText(ShadowRegistration.this, "The Shadow User created successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackMenu = new Intent(ShadowRegistration.this, LoginActivity.class);
                startActivity(BackMenu);
            }
        });




    }



}