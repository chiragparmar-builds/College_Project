package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.contact_manager.entities.Resume_info.Resume;

@Repository
public interface Resume_Repo extends JpaRepository<Resume,Integer> {

}
