package com.contactmanager.contact_manager.dao;

import com.contactmanager.contact_manager.entities.Resume_info.Educaion_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Educational_info_repo extends JpaRepository<Educaion_info,Integer> {
}
