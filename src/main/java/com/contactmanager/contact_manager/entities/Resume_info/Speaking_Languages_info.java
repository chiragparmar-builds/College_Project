package com.contactmanager.contact_manager.entities.Resume_info;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Speaking_Languages_info")
public class Speaking_Languages_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Speaking_Languages_info_id;

    private String Speaking_Language;
    private String Speaking_Language_Level;

    @ManyToOne
    private Resume resume_id;

    public int getSpeaking_Languages_info_id() {
        return Speaking_Languages_info_id;
    }

    public void setSpeaking_Languages_info_id(int speaking_Languages_info_id) {
        Speaking_Languages_info_id = speaking_Languages_info_id;
    }

    public String getSpeaking_Language() {
        return Speaking_Language;
    }

    public void setSpeaking_Language(String speaking_Language) {
        Speaking_Language = speaking_Language;
    }

    public String getSpeaking_Language_Level() {
        return Speaking_Language_Level;
    }

    public void setSpeaking_Language_Level(String speaking_Language_Level) {
        Speaking_Language_Level = speaking_Language_Level;
    }

    public Resume getResume_id() {
        return resume_id;
    }

    public void setResume_id(Resume resume_id) {
        this.resume_id = resume_id;
    }

    

    

    
}
