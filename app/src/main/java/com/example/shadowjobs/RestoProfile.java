package com.example.shadowjobs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shadowjobs.databinding.ActivityRestoProfileBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestoProfile extends DrawerBaseActivity {

    ActivityRestoProfileBinding activityRestoProfileBinding;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;
    String id;
    Button btnEdit, btnJobPost, btnJobsList;
    TextView txtBusinessName, txtAddress, txtBusinessPhone, txtBusinessEmail, txtRestoDesc;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_profile) {

            return false;
        } else if (item.getItemId() == R.id.nav_editProfile){

            passData();
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
        activityRestoProfileBinding = ActivityRestoProfileBinding.inflate(getLayoutInflater());
        setContentView(activityRestoProfileBinding.getRoot());
        allocateActivityTitle("Restaurant Profile");

        barChart =  findViewById(R.id.RestoBarChart);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "");
        barData =  new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(Color.BLACK);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        txtBusinessName = findViewById(R.id.txtBusinessName);
        txtAddress = findViewById(R.id.txtAddress);
        txtBusinessPhone = findViewById(R.id.txtBusinessPhone);
        txtBusinessEmail = findViewById(R.id.txtBusinessEmail);
        txtRestoDesc = findViewById(R.id.txtRestoDesc);
        btnEdit = findViewById(R.id.btnEditResto);
        btnJobPost = findViewById(R.id.btnJobPost);
        btnJobsList = findViewById(R.id.btnJobsList);

        showBusinessInfo();
        btnEdit.setOnClickListener(v -> passData());

        btnJobPost.setOnClickListener(view -> postJob());
        btnJobsList.setOnClickListener(view -> showMyJobs());
    }

    public void showBusinessInfo(){

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String restoName = intent.getStringExtra("business");
        String restoAddress = intent.getStringExtra("address");
        String restoPhone = intent.getStringExtra("phone");
        String restoEmail = intent.getStringExtra("email");
        String restoDesc = intent.getStringExtra("bio");

        txtBusinessName.setText(restoName);
        txtAddress.setText(restoAddress);
        txtBusinessPhone.setText(restoPhone);
        txtBusinessEmail.setText(restoEmail);
        txtRestoDesc.setText(restoDesc);

    }

    public void showMyJobs(){
        Intent intent = new Intent(RestoProfile.this, JobsActivity.class);
        intent.putExtra("loggedInUserId", id);
        intent.putExtra("loggedInUserType", "Restaurant");
        startActivity(intent);
    }

    public void postJob(){
        Intent intent = new Intent(RestoProfile.this, JobPostingActivity.class);
        intent.putExtra("loggedInUserId", id);
        //intent.putExtra("loggedInUserType", "Restaurant");
        startActivity(intent);
    }

    public void passData(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("restaurants");
        Query checkShadowDatabase = reference.orderByChild("id").equalTo(id);
        checkShadowDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String nameDb = snapshot.child(id).child("business").getValue(String.class);
                    String addressDb = snapshot.child(id).child("address").getValue(String.class);
                    String phoneDb = snapshot.child(id).child("phone").getValue(String.class);
                    String emailDb = snapshot.child(id).child("email").getValue(String.class);
                    String descDb = snapshot.child(id).child("bio").getValue(String.class);

                    Intent intent = new Intent(RestoProfile.this, EditRestoProfile.class);
                    intent.putExtra("id", id);
                    intent.putExtra("business", nameDb);
                    intent.putExtra("address", addressDb);
                    intent.putExtra("phone", phoneDb);
                    intent.putExtra("email", emailDb);
                    intent.putExtra("desc", descDb);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getBarEntries() {

        barEntriesArrayList = new ArrayList<>();

        barEntriesArrayList.add(new BarEntry(1, 0));
        barEntriesArrayList.add(new BarEntry(2, 8));
        barEntriesArrayList.add(new BarEntry(3, 0));
        barEntriesArrayList.add(new BarEntry(4, 4));
        barEntriesArrayList.add(new BarEntry(5, 3));
        barEntriesArrayList.add(new BarEntry(6, 6));
        barEntriesArrayList.add(new BarEntry(7, 5));

    }

    @Override
    public void onBackPressed(){}
}