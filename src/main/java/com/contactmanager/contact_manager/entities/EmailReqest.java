package com.contactmanager.contact_manager.entities;

public class EmailReqest {

    private String form;
    private String subject;
    private String message;

    
    public EmailReqest(String form, String subject, String message) {
        this.form = form;
        this.subject = subject;
        this.message = message;
    }

    
    public EmailReqest() {
    }


    public String getForm() {
        return form;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "EmailReqest [form=" + form + ", subject=" + subject + ", message=" + message + "]";
    }

    
}
