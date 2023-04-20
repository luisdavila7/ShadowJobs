package com.example.shadowjobs;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shadowjobs.databinding.ActivityJobsBinding;
import com.example.shadowjobs.model.Job;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobsActivity extends DrawerBaseActivity {

    ActivityJobsBinding activityJobsBinding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("JobPostings");
    RecyclerView recyclerView;
    JobsListRecyclerAdapter jobsListRecyclerAdapter;
    ArrayList<Job> jobList = new ArrayList<Job>();
    CoordinatorLayout coordinatorLayout;

    String loggedInUserId, loggedInUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJobsBinding = ActivityJobsBinding.inflate(getLayoutInflater());
        setContentView(activityJobsBinding.getRoot());
        allocateActivityTitle("Jobs");

        recyclerView = findViewById(R.id.recyclerViewJobsList);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        jobsListRecyclerAdapter = new JobsListRecyclerAdapter(jobList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jobsListRecyclerAdapter);

        Intent intent = getIntent();
        loggedInUserId = intent.getStringExtra("loggedInUserId");
        loggedInUserType = intent.getStringExtra("loggedInUserType");

        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        Job job = ds.getValue(Job.class);
                        if(loggedInUserType.equals("Shadow") && job.getShadowId().trim().length() == 0)
                            jobList.add(job);
                        else if (loggedInUserId.equals(job.getRestaurantId()))
                            jobList.add(job);
                    }

                    jobsListRecyclerAdapter.notifyDataSetChanged();

                }
            }
        });

        enableSwipe();
    }

    private void enableSwipe() {
        if(loggedInUserType.equals("Restaurant")){
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

        } else if(loggedInUserType.equals("Shadow")){


            SwipeToApply swipeToApply = new SwipeToApply(this) {

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                    final int pos = viewHolder.getAdapterPosition();
                    final Job job = jobsListRecyclerAdapter.getData().get(pos);

                    jobsListRecyclerAdapter.removeItem(pos);
                    databaseReference.child(job.getJobId()).removeValue();
                    job.setShadowId(loggedInUserId);
                    databaseReference.child(job.getJobId()).setValue(job);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "You applied for this job.",Snackbar.LENGTH_LONG);

                    snackbar.setAction("Undo it", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            jobsListRecyclerAdapter.restoreItem(job,pos);
                            databaseReference.child(job.getJobId()).removeValue();
                            job.setShadowId("");
                            databaseReference.child(job.getJobId()).setValue(job);
                        }
                    });

                    snackbar.show();
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToApply);
            itemTouchHelper.attachToRecyclerView(recyclerView);


        }

    }

}