package com.example.shadowjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.shadowjobs.model.Job;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobsActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("JobPostings");
    RecyclerView recyclerView;
    JobsListRecyclerAdapter jobsListRecyclerAdapter;

    ArrayList<Job> jobList = new ArrayList<Job>();
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        recyclerView = findViewById(R.id.recyclerViewJobsList);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        jobsListRecyclerAdapter = new JobsListRecyclerAdapter(jobList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jobsListRecyclerAdapter);

        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                //jobList = new ArrayList<Job>();
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        Job job = ds.getValue(Job.class);
                        jobList.add(job);
                    }

                    jobsListRecyclerAdapter.notifyDataSetChanged();

                }
            }
        });

        enableSwipeToDelete();
    }

    private void enableSwipeToDelete() {
        SwipeToDelete swipeToDelete = new SwipeToDelete(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                final int pos = viewHolder.getAdapterPosition();
                final Job job = jobsListRecyclerAdapter.getData().get(pos);

                jobsListRecyclerAdapter.removeItem(pos);

                databaseReference.child(job.getJobId()).removeValue();

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Job removed",Snackbar.LENGTH_LONG);

                snackbar.setAction("Undo it", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jobsListRecyclerAdapter.restoreItem(job,pos);

                        databaseReference.child(job.getJobId()).setValue(job);
                    }
                });

                snackbar.show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDelete);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

}