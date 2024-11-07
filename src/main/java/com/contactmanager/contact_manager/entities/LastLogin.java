package com.contactmanager.contact_manager.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LAST_LOGIN")
public class LastLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int LLid;

    private String email;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ManyToOne
    @JoinColumn(name = "last_login_user")
    private User last_login_user;


    public int getLLid() {
        return LLid;
    }

    public void setLLid(int lLid) {
        LLid = lLid;
    }

    public User getLast_login_user() {
        return last_login_user;
    }

    public void setLast_login_user(User last_login_user) {
        this.last_login_user = last_login_user;
    }

    public LastLogin() {
        super();
    }

    public LastLogin(LocalDateTime now) {
        //TODO Auto-generated constructor stub
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

}
