package com.contactmanager.contact_manager.entities.Resume_info;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Programing_Language_info")
public class Programing_Language_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Programing_Language_info_id;


    private String programming_Languages;
    private String programming_Technology;
    private String programming_Frameworks;
    private String programming_Database;

    @ManyToOne
    private Resume resume_id;

    public int getPrograming_Language_info_id() {
        return Programing_Language_info_id;
    }

    public void setPrograming_Language_info_id(int programing_Language_info_id) {
        Programing_Language_info_id = programing_Language_info_id;
    }

    public String getProgramming_Languages() {
        return programming_Languages;
    }

    public void setProgramming_Languages(String programming_Languages) {
        this.programming_Languages = programming_Languages;
    }

    public String getProgramming_Technology() {
        return programming_Technology;
    }

    public void setProgramming_Technology(String programming_Technology) {
        this.programming_Technology = programming_Technology;
    }

    public String getProgramming_Frameworks() {
        return programming_Frameworks;
    }

    public void setProgramming_Frameworks(String programming_Frameworks) {
        this.programming_Frameworks = programming_Frameworks;
    }

    public String getProgramming_Database() {
        return programming_Database;
    }

    public void setProgramming_Database(String programming_Database) {
        this.programming_Database = programming_Database;
    }

    public Resume getResume_id() {
        return resume_id;
    }

    public void setResume_id(Resume resume_id) {
        this.resume_id = resume_id;
    }

    

    

    
}
