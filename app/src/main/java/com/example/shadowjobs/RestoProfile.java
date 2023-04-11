package com.example.shadowjobs;

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

import java.util.ArrayList;

public class RestoProfile extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;

    Button btnEdit;
    TextView name, address, phone, email, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_profile);

        barChart =  findViewById(R.id.RestoBarChart);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "");
        barData =  new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(Color.BLACK);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        btnEdit = findViewById(R.id.btnEditResto);

        showBusinessInfo();
    }

    public void showBusinessInfo(){

        Intent intent = getIntent();
        String restoName = intent.getStringExtra("name");
        String restoAddress = intent.getStringExtra("address");
        String restoPhone = intent.getStringExtra("phone");
        String restoEmail = intent.getStringExtra("email");
        String restoDesc = intent.getStringExtra("desc");

        name.setText(restoName);
        address.setText(restoAddress);
        phone.setText(restoPhone);
        email.setText(restoEmail);
        desc.setText(restoDesc);

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