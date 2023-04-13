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
    String restoId, restoName, restoAddress, restoPhone, restoEmail, restoWebsite, restoDesc;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resto_profile);

        // reference "path" needs to be the same in ShadowRegistration, very important!!
        reference = FirebaseDatabase.getInstance().getReference("restaurants");

        Intent intent = new Intent();
        restoId = intent.getStringExtra("id");

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

            Intent backToProfile = new Intent(EditRestoProfile.this, RestoProfile.class);
            backToProfile.putExtra("id", restoId);
            backToProfile.putExtra("name", restoName);
            backToProfile.putExtra("address", restoAddress);
            backToProfile.putExtra("phone", restoPhone);
            backToProfile.putExtra("email", restoEmail);
            backToProfile.putExtra("website", restoWebsite);
            backToProfile.putExtra("desc", restoDesc);
            startActivity(backToProfile);

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

            reference.child(restoId).child("name").setValue(editRestoName.getText().toString());
            restoName = editRestoName.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isAddressChanged(){

        if(!restoAddress.equals(editRestoAddress.getText().toString())){

            reference.child(restoId).child("address").setValue(editRestoAddress.getText().toString());
            restoAddress = editRestoAddress.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isRestoPhoneChanged(){

        if(!restoPhone.equals(editRestoPhone.getText().toString())){

            reference.child(restoId).child("phone").setValue(editRestoPhone.getText().toString());
            restoPhone = editRestoPhone.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isRestoEmailChanged(){

        if(!restoEmail.equals(editRestoEmail.getText().toString())){

            reference.child(restoId).child("email").setValue(editRestoEmail.getText().toString());
            restoEmail = editRestoEmail.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isWebsiteChanged(){

        if(!restoWebsite.equals(editRestoWebsite.getText().toString())){

            reference.child(restoId).child("website").setValue(editRestoWebsite.getText().toString());
            restoWebsite = editRestoWebsite.getText().toString();
            return true;

        } else {

            return false;

        }

    }

    private boolean isRestoDescChanged(){

        if(!restoDesc.equals(editRestoDesc.getText().toString())){

            reference.child(restoId).child("desc").setValue(editRestoDesc.getText().toString());
            restoDesc = editRestoDesc.getText().toString();
            return true;

        } else {

            return false;

        }
    }
}
