 package com.example.shadowjobs;

 import android.content.Intent;
 import android.graphics.Color;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.TextView;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;

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
        btnEdit.setOnClickListener(v -> passData());

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

     public void passData(){

        String userId = null;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("shadows");
        Query checkShadowDatabase = reference.orderByChild("id").equalTo(userId);
        checkShadowDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fNameDb = snapshot.child(userId).child("fName").getValue(String.class);
                    String lNameDb = snapshot.child(userId).child("lName").getValue(String.class);
                    String phoneDb = snapshot.child(userId).child("phone").getValue(String.class);
                    String emailDb = snapshot.child(userId).child("email").getValue(String.class);
                    String descDb = snapshot.child(userId).child("desc").getValue(String.class);

                    Intent intent = new Intent(ShadowProfile.this, EditShadowProfile.class);
                    intent.putExtra("fName", fNameDb);
                    intent.putExtra("lName", lNameDb);
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

        barEntriesArrayList.add(new BarEntry(1f, 0));
        barEntriesArrayList.add(new BarEntry(1f, 8));
        barEntriesArrayList.add(new BarEntry(1f, 0));
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(1f, 3));
        barEntriesArrayList.add(new BarEntry(1f, 6));
        barEntriesArrayList.add(new BarEntry(1f, 5));

    }
}