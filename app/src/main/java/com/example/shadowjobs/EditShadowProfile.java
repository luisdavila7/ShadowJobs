package com.example.shadowjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditShadowProfile extends AppCompatActivity {

    EditText editFirstName, editLastName, editPhone, editEmail, editDesc;
    Button btnSave, btnBack;
    String shadowFirstName, shadowLastName, shadowPhone, shadowEmail, shadowDesc;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shadow_profile);

        // reference "path" needs to be the same in ShadowRegistration, very important!!
        reference = FirebaseDatabase.getInstance().getReference("shadows");

        editFirstName = findViewById(R.id.eTxtProfileShadowFirstName);
        editLastName = findViewById(R.id.eTxtProfileShadowLastName);
        editPhone = findViewById(R.id.eTxtShadowPhone);
        editEmail = findViewById(R.id.eTxtShadowEmail);
        editDesc = findViewById(R.id.eTxtShadowDesc);
        btnSave = findViewById(R.id.btnSaveShadowProfile);
        btnBack = findViewById(R.id.btnBackShadowProfile);
        showData();

        btnSave.setOnClickListener(v -> {

            if (isFirstNameChanged() || isLastNameChanged() || isPhoneChanged() || isEmailChanged() || isDescChanged()) {

                Toast.makeText(EditShadowProfile.this, "Saved", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(EditShadowProfile.this, "No Changed Found", Toast.LENGTH_SHORT).show();

            }

        });

        btnBack.setOnClickListener(v -> {

            Intent intent = new Intent(EditShadowProfile.this, ShadowProfile.class);
            intent.putExtra("fName", shadowFirstName);
            intent.putExtra("lName", shadowLastName);
            intent.putExtra("phone", shadowPhone);
            intent.putExtra("email", shadowEmail);
            intent.putExtra("desc", shadowDesc);
            startActivity(intent);

        });
    }

    public void showData(){

        Intent intent = getIntent();
        shadowFirstName = intent.getStringExtra("fName");
        shadowLastName = intent.getStringExtra("lName");
        shadowPhone = intent.getStringExtra("phone");
        shadowEmail = intent.getStringExtra("email");
        shadowDesc = intent.getStringExtra("desc");

        editFirstName.setText(shadowFirstName);
        editLastName.setText(shadowLastName);
        editPhone.setText(shadowPhone);
        editEmail.setText(shadowEmail);
        editDesc.setText(shadowDesc);

    }

    private boolean isFirstNameChanged(){

        if(!shadowFirstName.equals(editFirstName.getText().toString())){

            reference.child("???").child("fName").setValue(editFirstName.getText().toString());
            shadowFirstName = editFirstName.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isLastNameChanged(){

        if(!shadowLastName.equals(editLastName.getText().toString())){

            reference.child("???").child("lName").setValue(editLastName.getText().toString());
            shadowLastName = editLastName.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isPhoneChanged(){

        if(!shadowPhone.equals(editPhone.getText().toString())){

            reference.child("???").child("phone").setValue(editPhone.getText().toString());
            shadowPhone = editPhone.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isEmailChanged(){

        if(!shadowEmail.equals(editEmail.getText().toString())){

            reference.child("???").child("email").setValue(editEmail.getText().toString());
            shadowEmail = editEmail.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isDescChanged(){

        if(!shadowDesc.equals(editDesc.getText().toString())){

            reference.child("???").child("desc").setValue(editDesc.getText().toString());
            shadowDesc = editDesc.getText().toString();
            return true;

        } else {

            return false;

        }

    }
}