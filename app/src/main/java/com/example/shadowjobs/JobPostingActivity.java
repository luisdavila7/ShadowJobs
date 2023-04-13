package com.example.shadowjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shadowjobs.model.Job;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class JobPostingActivity extends AppCompatActivity {

    EditText position, address,contactName, contactPhone, description, salary;
    Button create;

    FirebaseDatabase database;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);

        position = findViewById(R.id.editTextPosition);
        address = findViewById(R.id.editTextAddress);
        contactName = findViewById(R.id.editTextContactName);
        contactPhone = findViewById(R.id.editTextContactPhone);
        description = findViewById(R.id.editTextDescription);
        salary = findViewById(R.id.editTextSalary);

        create = findViewById(R.id.btnJobPosting);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("JobPostings");

                UUID uuid = UUID.randomUUID();
                String jobId = uuid.toString();

                String jobPosition = position.getText().toString();
                String jobAddress = address.getText().toString();
                String jobContactName = contactName.getText().toString();
                String jobContactPhone = contactPhone.getText().toString();
                String jobDescription = description.getText().toString();
                Float jobSalary = Float.parseFloat(salary.getText().toString());

                Job job = new Job(jobId,null,jobPosition,jobAddress,jobContactName,jobContactPhone,jobDescription,jobSalary);

                reference.child(jobId).setValue(job);

                Toast.makeText(JobPostingActivity.this, "The job posted successfully.", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(JobPostingActivity.this,LoginActivity.class));
            }
        });
    }
}