package com.contactmanager.contact_manager.entities.Resume_info;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Educaion_info")
public class Educaion_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Educaion_info_id;

    private String Cource_Name;
    private String Institute_Name;
    private String CGPA;
    private String Percentage;
    private String Educaion_City;
    private String Educaion_Time;

    @ManyToOne
    private Resume resume_id;

    public int getEducaion_info_id() {
        return Educaion_info_id;
    }

    public void setEducaion_info_id(int educaion_info_id) {
        Educaion_info_id = educaion_info_id;
    }

    public String getCource_Name() {
        return Cource_Name;
    }

    public void setCource_Name(String cource_Name) {
        Cource_Name = cource_Name;
    }

    public String getInstitute_Name() {
        return Institute_Name;
    }

    public void setInstitute_Name(String institute_Name) {
        Institute_Name = institute_Name;
    }

    public String getCGPA() {
        return CGPA;
    }

    public void setCGPA(String cGPA) {
        CGPA = cGPA;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getEducaion_City() {
        return Educaion_City;
    }

    public void setEducaion_City(String educaion_City) {
        Educaion_City = educaion_City;
    }

    public String getEducaion_Time() {
        return Educaion_Time;
    }

    public void setEducaion_Time(String educaion_Time) {
        Educaion_Time = educaion_Time;
    }

    public Resume getResume_id() {
        return resume_id;
    }

    public void setResume_id(Resume resume_id) {
        this.resume_id = resume_id;
    }

    

    // @Override
    // public String toString() {
    //     return "Educaion_info{" +
    //             "Educaion_info_id=" + Educaion_info_id +
    //             ", Cource_Name='" + Cource_Name + '\'' +
    //             ", Institute_Name='" + Institute_Name + '\'' +
    //             ", CGPA='" + CGPA + '\'' +
    //             ", Percentage='" + Percentage + '\'' +
    //             ", Educaion_City='" + Educaion_City + '\'' +
    //             ", Educaion_Time='" + Educaion_Time + '\'' +
    //             ", U_educaion_infos=" + U_educaion_infos +
    //             '}';
    // }
}
