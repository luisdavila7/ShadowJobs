 package com.example.shadowjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

 public class ShadowProfile extends AppCompatActivity {/**/

    BarChart barChart;

    BarData barData;

    BarDataSet barDataSet;

    ArrayList barEntriesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_profile);

        barChart =  findViewById(R.id.BarChart);

        getBarEntries();

        barDataSet = new BarDataSet(barEntriesArrayList, "");

        barData =  new BarData(barDataSet);

        barChart.setData(barData);

        barDataSet.setColors(Color.BLACK);

        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
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