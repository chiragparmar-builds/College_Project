package com.contactmanager.contact_manager.entities.Resume_info;

import java.util.List;

import com.contactmanager.contact_manager.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private User resume;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume_id")
    private List<Certifications_info> certifications_infos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume_id")
    private List<Educaion_info> educaion_infos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume_id")
    private List<Personal_info> personal_infos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume_id")
    private List<Programing_Language_info> programing_Language_infos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume_id")
    private List<Project_info> project_infos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume_id")
    private List<Speaking_Languages_info> speaking_Languages_infos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getResume() {
        return resume;
    }

    public void setResume(User resume) {
        this.resume = resume;
    }

    public List<Certifications_info> getCertifications_infos() {
        return certifications_infos;
    }

    public void setCertifications_infos(List<Certifications_info> certifications_infos) {
        this.certifications_infos = certifications_infos;
    }

    public List<Educaion_info> getEducaion_infos() {
        return educaion_infos;
    }

    public void setEducaion_infos(List<Educaion_info> educaion_infos) {
        this.educaion_infos = educaion_infos;
    }

    public List<Personal_info> getPersonal_infos() {
        return personal_infos;
    }

    public void setPersonal_infos(List<Personal_info> personal_infos) {
        this.personal_infos = personal_infos;
    }

    public List<Programing_Language_info> getPrograming_Language_infos() {
        return programing_Language_infos;
    }

    public void setPrograming_Language_infos(List<Programing_Language_info> programing_Language_infos) {
        this.programing_Language_infos = programing_Language_infos;
    }

    public List<Project_info> getProject_infos() {
        return project_infos;
    }

    public void setProject_infos(List<Project_info> project_infos) {
        this.project_infos = project_infos;
    }

    public List<Speaking_Languages_info> getSpeaking_Languages_infos() {
        return speaking_Languages_infos;
    }

    public void setSpeaking_Languages_infos(List<Speaking_Languages_info> speaking_Languages_infos) {
        this.speaking_Languages_infos = speaking_Languages_infos;
    }

    // @Override
    // public String toString() {
    //     return "Resume{" +
    //             "id=" + id +
    //             ", resume=" + resume +
    //             ", certifications_infos=" + certifications_infos +
    //             ", educaion_infos=" + educaion_infos +
    //             ", personal_infos=" + personal_infos +
    //             ", programing_Language_infos=" + programing_Language_infos +
    //             ", project_infos=" + project_infos +
    //             ", speaking_Languages_infos=" + speaking_Languages_infos +
    //             '}';
    // }
}