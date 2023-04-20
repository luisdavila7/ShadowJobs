package com.example.shadowjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.shadowjobs.databinding.ActivityEditRestoProfileBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditRestoProfile extends DrawerBaseActivity {

    ActivityEditRestoProfileBinding activityEditRestoProfileBinding;
    EditText editRestoName, editRestoAddress, editRestoPhone, editRestoEmail, editRestoDesc;
    Button btnSave, btnBack;
    String restoId, restoName, restoAddress, restoPhone, restoEmail, restoDesc;
    DatabaseReference reference;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_profile) {

            passData();
            return false;
        } else if (item.getItemId() == R.id.nav_editProfile){

            return false;
        } else if (item.getItemId() == R.id.nav_jobs){
            showMyJobs();
            return false;
        } else{
            return super.onNavigationItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditRestoProfileBinding = ActivityEditRestoProfileBinding.inflate(getLayoutInflater());
        setContentView(activityEditRestoProfileBinding.getRoot());
        allocateActivityTitle("Edit Restaurant Profile");



        reference = FirebaseDatabase.getInstance().getReference("restaurants");

        editRestoName = findViewById(R.id.eTxtRestoName);
        editRestoAddress = findViewById(R.id.eTxtRestoAddress);
        editRestoPhone = findViewById(R.id.eTxtRestoPhone);
        editRestoEmail = findViewById(R.id.eTxtRestoEmail);
        editRestoDesc = findViewById(R.id.eTxtRestoDesc);
        btnSave = findViewById(R.id.btnSaveRestoProfile);
        btnBack = findViewById(R.id.btnBackRestoProfile);
        showData();

        btnSave.setOnClickListener(v -> {

            if (isRestoNameChanged() || isAddressChanged() || isRestoPhoneChanged() || isRestoEmailChanged() || isRestoDescChanged()) {

                Toast.makeText(EditRestoProfile.this, "Saved", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(EditRestoProfile.this, "No Changed Found", Toast.LENGTH_SHORT).show();

            }

        });

        btnBack.setOnClickListener(v -> {

            passData();

        });
    }

    public void passData(){
        Intent backToProfile = new Intent(EditRestoProfile.this, RestoProfile.class);
        backToProfile.putExtra("id", restoId);
        backToProfile.putExtra("business", restoName);
        backToProfile.putExtra("address", restoAddress);
        backToProfile.putExtra("phone", restoPhone);
        backToProfile.putExtra("email", restoEmail);
        backToProfile.putExtra("desc", restoDesc);
        startActivity(backToProfile);
    }

    public void showMyJobs(){
        Intent intent = new Intent(EditRestoProfile.this, JobsActivity.class);
        intent.putExtra("loggedInUserId", restoId);
        intent.putExtra("loggedInUserType", "Restaurant");
        startActivity(intent);
    }

    public void showData(){

        Intent intent = getIntent();
        restoId = intent.getStringExtra("id");
        restoName = intent.getStringExtra("business");
        restoAddress = intent.getStringExtra("address");
        restoPhone = intent.getStringExtra("phone");
        restoEmail = intent.getStringExtra("email");
        restoDesc = intent.getStringExtra("desc");

        editRestoName.setText(restoName);
        editRestoAddress.setText(restoAddress);
        editRestoPhone.setText(restoPhone);
        editRestoEmail.setText(restoEmail);
        editRestoDesc.setText(restoDesc);

    }

    private boolean isRestoNameChanged(){

        if(!restoName.equals(editRestoName.getText().toString())){

            reference.child(restoId).child("business").setValue(editRestoName.getText().toString());
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
