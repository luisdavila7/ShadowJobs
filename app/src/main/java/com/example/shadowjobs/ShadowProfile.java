 package com.example.shadowjobs;

 import android.content.Intent;
 import android.graphics.Color;
 import android.os.Bundle;
 import android.widget.Button;
 import android.widget.TextView;

 import androidx.appcompat.app.AppCompatActivity;

 import com.github.mikephil.charting.charts.BarChart;
 import com.github.mikephil.charting.data.BarData;
 import com.github.mikephil.charting.data.BarDataSet;
 import com.github.mikephil.charting.data.BarEntry;

 import java.util.ArrayList;

 public class ShadowProfile extends AppCompatActivity {/**/

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;

    Button btnEdit;
    TextView fName, lName, phone, email, desc;

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

        btnEdit = findViewById(R.id.btnEditShadow);

        showUserData();
        btnEdit.setOnClickListener(v -> startActivity(new Intent(ShadowProfile.this, EditShadowProfile.class)));

    }

    public void showUserData(){

        Intent intent = getIntent();
        String userFName = intent.getStringExtra("fName");
        String userLName = intent.getStringExtra("lName");
        String userPhone = intent.getStringExtra("phone");
        String userEmail = intent.getStringExtra("email");
        String userDesc = intent.getStringExtra("desc");

        fName.setText(userFName);
        lName.setText(userLName);
        phone.setText(userPhone);
        email.setText(userEmail);
        desc.setText(userDesc);

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