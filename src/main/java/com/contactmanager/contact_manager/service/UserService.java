package com.contactmanager.contact_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getAdminByUsername(String username) {
        return userRepository.getUserByUserName(username);
    }

    public List<User> getAllActiveUsers() {
        return userRepository.getAllActiveUsers();
    }

    public long getTotalUsers() {
        return userRepository.count();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean updateUser(int id, String name, String email, String role) {

        User user = getUser(id);

        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setRole(role);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public Page<User> getUsers(int page, String keyword) {

        Pageable pageable = PageRequest.of(page, 10); // ✅ 10 records per page

        if (keyword != null && !keyword.trim().isEmpty()) {
            return userRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }

        return userRepository.findAll(pageable);
    }

    public void changeRole(int id, String role) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setRole(role);
            userRepository.save(user);
        }
    }
}