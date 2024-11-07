package com.contactmanager.contact_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.contact_manager.entities.Resume_info.Programing_Language_info;

@Repository
public interface Programmin_Lan_Repo extends JpaRepository<Programing_Language_info,Integer> {

}
