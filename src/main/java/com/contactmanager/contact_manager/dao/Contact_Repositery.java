package com.contactmanager.contact_manager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contactmanager.contact_manager.entities.contact;

public interface Contact_Repositery extends JpaRepository<contact, Integer> {

  @Query("SELECT c.message FROM contact c WHERE c.cid = :cid")
  public String getMessageByCid(@Param("cid") int cid);

  // public contact findall();
  Page<contact> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMessageContainingIgnoreCase(
          String name,
          String email,
          String message,
          Pageable pageable
  );

  @Query("SELECT c FROM contact c WHERE " +
          "(:keyword IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
          "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
          "OR LOWER(c.message) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
          "AND (:isRead IS NULL OR c.isRead = :isRead)")
  Page<contact> filterMessages(
          @Param("keyword") String keyword,
          @Param("isRead") Boolean isRead,
          Pageable pageable
  );

  long countByIsReadFalse();
}