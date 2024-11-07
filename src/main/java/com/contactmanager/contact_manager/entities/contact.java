package com.contactmanager.contact_manager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CONTACT-US")
public class contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters")
    private String name;


    // private String secondName;
    // private String work;
    @Email(message = "Enter The Valid Email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[a-zA-Z]{2,4}$")
    private String email;
    
    @NotNull(message = "Phone No cannot be null")
    @Size(min = 10, message = "Phone No must be at least 10 number Long")
    private String phone;
    
    
    // private String image;
    private String message;

    @ManyToOne
    private User user;
    
    public int getCid() {
        return cid;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // public String getSecondName() {
    //     return secondName;
    // }
    // public void setSecondName(String secondName) {
    //     this.secondName = secondName;
    // }
    // public String getWork() {
    //     return work;
    // }
    // public void setWork(String work) {
    //     this.work = work;
    // }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    // public String getImage() {
    //     return image;
    // }
    // public void setImage(String image) {
    //     this.image = image;
    // }
    // public String getDescription() {
    //     return description;
    // }
    // public void setDescription(String description) {
    //     this.description = description;
    // }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
}
