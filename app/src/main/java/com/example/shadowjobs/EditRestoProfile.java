package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditRestoProfile extends AppCompatActivity {

    EditText editRestoName, editRestoAddress, editRestoPhone, editRestoEmail, editRestoWebsite, editRestoDesc;
    Button btnSave, btnBack;
    String restoName, restoAddress, restoPhone, restoEmail, restoWebsite, restoDesc;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resto_profile);

        // reference "path" needs to be the same in ShadowRegistration, very important!!
        reference = FirebaseDatabase.getInstance().getReference("restaurants");

        editRestoName = findViewById(R.id.eTxtRestoName);
        editRestoAddress = findViewById(R.id.eTxtRestoAddress);
        editRestoPhone = findViewById(R.id.eTxtRestoPhone);
        editRestoEmail = findViewById(R.id.eTxtRestoEmail);
        editRestoWebsite = findViewById(R.id.eTxtRestoWebsite);
        editRestoDesc = findViewById(R.id.eTxtRestoDesc);
        btnSave = findViewById(R.id.btnSaveRestoProfile);
        btnBack = findViewById(R.id.btnBackRestoProfile);
        showData();

        btnSave.setOnClickListener(v -> {

            if (isRestoNameChanged() || isAddressChanged() || isRestoPhoneChanged() || isRestoEmailChanged() || isWebsiteChanged() || isRestoDescChanged()) {

                Toast.makeText(EditRestoProfile.this, "Saved", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(EditRestoProfile.this, "No Changed Found", Toast.LENGTH_SHORT).show();

            }

        });

        btnBack.setOnClickListener(v -> {

            Intent intent = new Intent(EditRestoProfile.this, RestoProfile.class);
            intent.putExtra("name", restoName);
            intent.putExtra("address", restoAddress);
            intent.putExtra("phone", restoPhone);
            intent.putExtra("email", restoEmail);
            intent.putExtra("website", restoWebsite);
            intent.putExtra("desc", restoDesc);
            startActivity(intent);

        });
    }

    public void showData(){

        Intent intent = getIntent();
        restoName = intent.getStringExtra("name");
        restoAddress = intent.getStringExtra("address");
        restoPhone = intent.getStringExtra("phone");
        restoEmail = intent.getStringExtra("email");
        restoWebsite = intent.getStringExtra("website");
        restoDesc = intent.getStringExtra("desc");

        editRestoName.setText(restoName);
        editRestoAddress.setText(restoAddress);
        editRestoPhone.setText(restoPhone);
        editRestoEmail.setText(restoEmail);
        editRestoWebsite.setText(restoWebsite);
        editRestoDesc.setText(restoDesc);

    }

    private boolean isRestoNameChanged(){

        if(!restoName.equals(editRestoName.getText().toString())){

            reference.child("???").child("name").setValue(editRestoName.getText().toString());
            restoName = editRestoName.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isAddressChanged(){

        if(!restoAddress.equals(editRestoAddress.getText().toString())){

            reference.child("???").child("address").setValue(editRestoAddress.getText().toString());
            restoAddress = editRestoAddress.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isRestoPhoneChanged(){

        if(!restoPhone.equals(editRestoPhone.getText().toString())){

            reference.child("???").child("phone").setValue(editRestoPhone.getText().toString());
            restoPhone = editRestoPhone.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isRestoEmailChanged(){

        if(!restoEmail.equals(editRestoEmail.getText().toString())){

            reference.child("???").child("email").setValue(editRestoEmail.getText().toString());
            restoEmail = editRestoEmail.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isWebsiteChanged(){

        if(!restoWebsite.equals(editRestoWebsite.getText().toString())){

            reference.child("???").child("website").setValue(editRestoWebsite.getText().toString());
            restoWebsite = editRestoWebsite.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isRestoDescChanged(){

        if(!restoDesc.equals(editRestoDesc.getText().toString())){

            reference.child("???").child("desc").setValue(editRestoDesc.getText().toString());
            restoDesc = editRestoDesc.getText().toString();
            return true;

        } else {

            return false;

        }
    }
}
