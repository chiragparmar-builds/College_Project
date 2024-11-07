package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.contact_manager.entities.Resume_info.Speaking_Languages_info;

@Repository
public interface Speaking_Language_Repo extends JpaRepository<Speaking_Languages_info,Integer> {

}
