package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.contact_manager.entities.LastLogin;

@Repository
public interface UserLoginHistoryRepository extends JpaRepository<LastLogin, Integer> {
}