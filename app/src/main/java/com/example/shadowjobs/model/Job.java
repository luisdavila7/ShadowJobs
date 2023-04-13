package com.example.shadowjobs.model;

public class Job {
    private String jobId;
    private String restaurantId;
    private String position;
    private String address;
    private String contactName;
    private String contactPhoneNumber;
    private String description;
    private Float salary;

    public Job(String jobId, String restaurantId, String position, String address, String contactName, String contactPhoneNumber, String description, Float salary) {
        this.jobId = jobId;
        this.restaurantId = restaurantId;
        this.position = position;
        this.address = address;
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.description = description;
        this.salary = salary;
    }

    public String getJobId() {
        return jobId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}
