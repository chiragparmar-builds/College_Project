package com.contactmanager.contact_manager.entities.Resume_info;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Personal_info")
public class Personal_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Personal_info_id;


    private String Full_Name;
    private String E_mail;
    private String Phone_Number;
    private String City;
    private String LinkedIn_ID;
    private String GitHub_ID;

    @ManyToOne
    private Resume resume_id;

    public int getPersonal_info_id() {
        return Personal_info_id;
    }

    public void setPersonal_info_id(int personal_info_id) {
        Personal_info_id = personal_info_id;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLinkedIn_ID() {
        return LinkedIn_ID;
    }

    public void setLinkedIn_ID(String linkedIn_ID) {
        LinkedIn_ID = linkedIn_ID;
    }

    public String getGitHub_ID() {
        return GitHub_ID;
    }

    public void setGitHub_ID(String gitHub_ID) {
        GitHub_ID = gitHub_ID;
    }

    public Resume getResume_id() {
        return resume_id;
    }

    public void setResume_id(Resume resume_id) {
        this.resume_id = resume_id;
    }

    

    
}
