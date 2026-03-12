package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.contact_manager.entities.Resume_info.Project_info;

@Repository
public interface Project_repo extends JpaRepository<Project_info,Integer> {

}
