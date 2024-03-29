 package com.example.shadowjobs;

 import android.content.Intent;
 import android.graphics.Color;
 import android.os.Bundle;
 import android.view.MenuItem;
 import android.widget.Button;
 import android.widget.TextView;

 import androidx.annotation.NonNull;

 import com.example.shadowjobs.databinding.ActivityShadowProfileBinding;
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

 public class ShadowProfile extends DrawerBaseActivity {

     ActivityShadowProfileBinding activityShadowProfileBinding;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;

    String id;

    Button btnEdit,btnJobList;
    TextView fName, lName, phone, email, desc;

     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         if (item.getItemId() == R.id.nav_profile) {

             return false;
         } else if (item.getItemId() == R.id.nav_editProfile){

             passData();
             return false;
         } else if (item.getItemId() == R.id.nav_jobs){
             showAllJobs();
             return false;
         }
         else{
             return super.onNavigationItemSelected(item);
         }
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShadowProfileBinding = ActivityShadowProfileBinding.inflate(getLayoutInflater());
        setContentView(activityShadowProfileBinding.getRoot());
        allocateActivityTitle("Shadow Profile");

        barChart =  findViewById(R.id.BarChart);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "");
        barData =  new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(Color.BLACK);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        fName = findViewById(R.id.txtFirstName);
        lName = findViewById(R.id.txtLastName);
        phone = findViewById(R.id.txtMobile);
        email = findViewById(R.id.txtEmail);
        desc = findViewById(R.id.shadowDesc);

        btnEdit = findViewById(R.id.btnEditShadow);
        btnJobList = findViewById(R.id.btnShadowGotoJobs);

        showShadowData();
        btnEdit.setOnClickListener(v -> passData());
        btnJobList.setOnClickListener(v -> showAllJobs());

    }

    public void showShadowData(){

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
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

     public void showAllJobs(){
         Intent intent = new Intent(ShadowProfile.this, JobsActivity.class);
         StringBuilder applyStatus = new StringBuilder().append(fName.getText()).append(" ").append(lName.getText()).append(" has applied (Phone: ").append(phone.getText()).append(").");
         intent.putExtra("loggedInUserId", applyStatus.toString());
         intent.putExtra("loggedInUserType", "Shadow");
         startActivity(intent);
     }
     public void passData(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("shadows");
        Query checkShadowDatabase = reference.orderByChild("id").equalTo(id);
        checkShadowDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fNameDb = snapshot.child(id).child("fName").getValue(String.class);
                    String lNameDb = snapshot.child(id).child("lName").getValue(String.class);
                    String phoneDb = snapshot.child(id).child("phone").getValue(String.class);
                    String emailDb = snapshot.child(id).child("email").getValue(String.class);
                    String descDb = snapshot.child(id).child("desc").getValue(String.class);

                    Intent intent = new Intent(ShadowProfile.this, EditShadowProfile.class);
                    intent.putExtra("id", id);
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

        barEntriesArrayList.add(new BarEntry(0, 0));
        barEntriesArrayList.add(new BarEntry(1, 8));
        barEntriesArrayList.add(new BarEntry(2, 0));
        barEntriesArrayList.add(new BarEntry(3, 4));
        barEntriesArrayList.add(new BarEntry(4, 3));
        barEntriesArrayList.add(new BarEntry(5, 6));
        barEntriesArrayList.add(new BarEntry(6, 5));

    }

     @Override
     public void onBackPressed() {}
 }