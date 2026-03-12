package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.contact_manager.entities.Resume_info.Personal_info;

@Repository
public interface Personal_Info_Repo extends JpaRepository<Personal_info,Integer> {

}
