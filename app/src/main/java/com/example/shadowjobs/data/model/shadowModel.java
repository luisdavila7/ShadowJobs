package com.example.shadowjobs.data.model;

public class shadowModel {

    String fName, lName, phone, email, desc;

    public shadowModel(String fName, String lName, String  phone, String  email, String  desc){

        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.desc = desc;

    }

    public shadowModel(){}

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
