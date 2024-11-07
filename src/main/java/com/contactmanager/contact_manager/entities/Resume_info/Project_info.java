package com.contactmanager.contact_manager.entities.Resume_info;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Project_info")
public class Project_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Project_info_id;

    private String Project_Name;
    private String Project_Technology;
    private String Project_DataBase;
    private String Project_Discription;

    @ManyToOne
    private Resume resume_id;

    public int getProject_info_id() {
        return Project_info_id;
    }

    public void setProject_info_id(int project_info_id) {
        Project_info_id = project_info_id;
    }

    public String getProject_Name() {
        return Project_Name;
    }

    public void setProject_Name(String project_Name) {
        Project_Name = project_Name;
    }

    public String getProject_Technology() {
        return Project_Technology;
    }

    public void setProject_Technology(String project_Technology) {
        Project_Technology = project_Technology;
    }

    public String getProject_DataBase() {
        return Project_DataBase;
    }

    public void setProject_DataBase(String project_DataBase) {
        Project_DataBase = project_DataBase;
    }

    public String getProject_Discription() {
        return Project_Discription;
    }

    public void setProject_Discription(String project_Discription) {
        Project_Discription = project_Discription;
    }

    public Resume getResume_id() {
        return resume_id;
    }

    public void setResume_id(Resume resume_id) {
        this.resume_id = resume_id;
    }

    
}
