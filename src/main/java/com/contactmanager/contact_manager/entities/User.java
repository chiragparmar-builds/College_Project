package com.contactmanager.contact_manager.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.contactmanager.contact_manager.entities.Resume_info.Resume;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String name;

    @Email(message = "Enter The Valid Email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[a-zA-Z]{2,4}$")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters Long")
    private String password;

    private Date dob;
    private String role;
    private String Gender;
    private String image;
    private boolean enables;
    private String imageurl;
    private String about;
    private int Is_Alive;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<contact> contacts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "last_login_user")
    private List<LastLogin> dd = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resume")
    private List<Resume> resumes = new ArrayList<>();

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public List<LastLogin> getDd() {
        return dd;
    }

    public void setDd(List<LastLogin> dd) {
        this.dd = dd;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnables() {
        return enables;
    }

    public void setEnables(boolean enables) {
        this.enables = enables;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<contact> contacts) {
        this.contacts = contacts;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", dob=" + dob
                + ", role=" + role + ", Gender=" + Gender + ", image=" + image + ", enables=" + enables + ", imageurl="
                + imageurl + ", about=" + about + ", contacts=" + contacts + "]";
    }

    public void setIsAlive(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIsAlive'");
    }

    public int getIs_Alive() {
        return Is_Alive;
    }

    public void setIs_Alive(int is_Alive) {
        Is_Alive = is_Alive;
    }
}
