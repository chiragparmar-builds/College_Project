package com.contactmanager.contact_manager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.contactmanager.contact_manager.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.email = :email")
    public User getUserByUserName(@Param("email") String email);

    // @Query("SELECT COUNT(u.id) FROM User u WHERE u.is_Alive = 1")
    // long countByIsAlive();

//    @Query("SELECT u FROM User u WHERE u.Is_Alive =:1")
    // public List<User> getUserByIs_Alive();

    // @Query(value = "SELECT * FROM user_details WHERE is_alive = 1", nativeQuery = true)
    // List<User> getAllActiveUsers();


    // @Query("SELECT u FROM User u WHERE u.is_alive = 1")
    // List<User> getAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.Is_Alive = 1 AND u.role = 'ROLE_USER'")
    List<User> getAllActiveUsers();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.Is_Alive = 0 WHERE id = :id")
    int updateUserById(@Param("id") int id);
}
