package com.example.shadowjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shadowjobs.model.Job;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    JobsListRecyclerAdapter jobsListRecyclerAdapter;

    ArrayList<Job> jobList;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("JobPostings");

        recyclerView = findViewById(R.id.recyclerViewJobsList);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        String jobId = ds.getKey();
                        jobList.add(ds.child(jobId).getValue(Job.class));
                    }

                    jobsListRecyclerAdapter = new JobsListRecyclerAdapter(jobList);
                    recyclerView.setAdapter(jobsListRecyclerAdapter);
                }
            }
        });

    }
}