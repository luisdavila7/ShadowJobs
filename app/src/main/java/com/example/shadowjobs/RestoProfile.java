package com.example.shadowjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

public class RestoProfile extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;

    String id;

    Button btnEdit;
    TextView name, address, phone, email, website, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_profile);

        Intent intent = new Intent();

        barChart =  findViewById(R.id.RestoBarChart);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "");
        barData =  new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(Color.BLACK);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        id = intent.getStringExtra("id");

        btnEdit = findViewById(R.id.btnEditResto);

        showBusinessInfo();
        btnEdit.setOnClickListener(v -> passData());
    }

    public void showBusinessInfo(){

        Intent intent = getIntent();
        String restoName = intent.getStringExtra("name");
        String restoAddress = intent.getStringExtra("address");
        String restoPhone = intent.getStringExtra("phone");
        String restoEmail = intent.getStringExtra("email");
//        String restoWebsite = intent.getStringExtra("website");
//        String restoDesc = intent.getStringExtra("desc");

        name.setText(restoName);
        address.setText(restoAddress);
        phone.setText(restoPhone);
        email.setText(restoEmail);
//        website.setText(restoWebsite);
//        desc.setText(restoDesc);

    }

    public void passData(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("restaurants");
        Query checkShadowDatabase = reference.orderByChild("id").equalTo(id);
        checkShadowDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String nameDb = snapshot.child(id).child("name").getValue(String.class);
                    String addressDb = snapshot.child(id).child("address").getValue(String.class);
                    String phoneDb = snapshot.child(id).child("phone").getValue(String.class);
                    String emailDb = snapshot.child(id).child("email").getValue(String.class);
                    String websiteDb = snapshot.child(id).child("website").getValue(String.class);
                    String descDb = snapshot.child(id).child("desc").getValue(String.class);

                    Intent intent = new Intent(RestoProfile.this, EditRestoProfile.class);
                    intent.putExtra("id", id);
                    intent.putExtra("name", nameDb);
                    intent.putExtra("address", addressDb);
                    intent.putExtra("phone", phoneDb);
                    intent.putExtra("email", emailDb);
                    intent.putExtra("website", websiteDb);
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

        barEntriesArrayList.add(new BarEntry(1f, 0));
        barEntriesArrayList.add(new BarEntry(1f, 8));
        barEntriesArrayList.add(new BarEntry(1f, 0));
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(1f, 3));
        barEntriesArrayList.add(new BarEntry(1f, 6));
        barEntriesArrayList.add(new BarEntry(1f, 5));

    }
}