package com.example.shadowjobs.model;

public class restoModel {
    String id, name, address, phone, email, password, bio;

    public restoModel(String id, String name, String email, String password, String address, String phone, String bio) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
    }

    restoModel() {
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getBio(){
        return bio;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void SetBio(String bio){
        this.bio = bio;
    }
}