package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contactmanager.contact_manager.entities.contact;

public interface Contact_Repositery extends JpaRepository<contact, Integer> {

   @Query("SELECT c.message FROM contact c WHERE c.cid = :cid")
   public String getMessageByCid(@Param("cid") int cid);

//    public contact findall();
}