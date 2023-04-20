package com.example.shadowjobs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shadowjobs.model.Job;

import java.util.ArrayList;

public class JobsListRecyclerAdapter extends RecyclerView.Adapter<JobsListRecyclerAdapter.MyViewHolder>{

    private ArrayList<Job> jobList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view_row, parent, false);
        return new MyViewHolder(view);
    }

    public JobsListRecyclerAdapter(ArrayList<Job> jobList){
        this.jobList = jobList;
    }

    @Override
    public void onBindViewHolder(@NonNull JobsListRecyclerAdapter.MyViewHolder holder, int position) {
        holder.jobPosition.setText(jobList.get(position).getPosition());
        holder.jobSalary.setText(jobList.get(position).getSalary().toString());
        holder.jobDescription.setText(jobList.get(position).getDescription());
        holder.jobApplyStatus.setText(jobList.get(position).getShadowId().trim() == "" ? "This job is available." : jobList.get(position).getShadowId());

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView jobPosition, jobSalary, jobDescription, jobApplyStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jobPosition =  itemView.findViewById(R.id.textViewJobPosition);
            jobSalary =  itemView.findViewById(R.id.textViewJobSalary);
            jobDescription =  itemView.findViewById(R.id.textViewJobDescription);
            jobApplyStatus = itemView.findViewById(R.id.textViewJobApplyStatus);

        }

    }

    public ArrayList<Job> getData(){
        return jobList;
    }

    public void removeItem(int pos){
        jobList.remove(pos);
        notifyItemRemoved(pos);
    }

    public void restoreItem(Job job,int pos){
        jobList.add(pos,job);
        notifyItemInserted(pos);
    }
}
