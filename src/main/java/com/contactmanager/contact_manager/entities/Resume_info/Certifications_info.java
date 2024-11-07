package com.contactmanager.contact_manager.entities.Resume_info;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Certifications_info")
public class Certifications_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Certifications_info_id;

    private String Certificate_Name;
    private String Certificate_Link;

    @ManyToOne
    private Resume resume_id;

    public int getCertifications_info_id() {
        return Certifications_info_id;
    }

    public void setCertifications_info_id(int certifications_info_id) {
        Certifications_info_id = certifications_info_id;
    }

    public String getCertificate_Name() {
        return Certificate_Name;
    }

    public void setCertificate_Name(String certificate_Name) {
        Certificate_Name = certificate_Name;
    }

    public String getCertificate_Link() {
        return Certificate_Link;
    }

    public void setCertificate_Link(String certificate_Link) {
        Certificate_Link = certificate_Link;
    }

    public Resume getResume_id() {
        return resume_id;
    }

    public void setResume_id(Resume resume_id) {
        this.resume_id = resume_id;
    }


    
}
