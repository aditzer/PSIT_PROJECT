package com.example.psit_project;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NewData {
    private String Admin,Fid,Name,Department,Designation,Contact,Whatsapp,Cabin,Extension,Email,Password;
    public NewData(){

    }
    public NewData(String admin,String cabin,String contact,String department,String designation,String email,String extension,String fid,String name,String password,String whatsapp){
        Admin=admin;
        Cabin=cabin;
        Contact=contact;
        Department=department;
        Designation=designation;
        Email=email;
        Extension=extension;
        Fid=fid;
        Name=name;
        Password=password;
        Whatsapp=whatsapp;
    }

    public String getFid() {
        return Fid;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public void setFid(String fid) {
        Fid = fid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getWhatsapp() {
        return Whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        Whatsapp = whatsapp;
    }

    public String getCabin() {
        return Cabin;
    }

    public void setCabin(String cabin) {
        Cabin = cabin;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) { Extension = extension; }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
