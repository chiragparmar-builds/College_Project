package com.contactmanager.contact_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.contactmanager.contact_manager.dao.Contact_Repositery;
import com.contactmanager.contact_manager.entities.contact;

@Service
public class ContactService {

    @Autowired
    private Contact_Repositery contactRepository;

    public List<contact> getAllMessages() {
        return contactRepository.findAll();
    }

    public int getTotalMessages() {
        return (int) contactRepository.count();
    }

    public String getMessageById(int id) {
        return contactRepository.getMessageByCid(id);
    }

    public void deleteMessage(int id) {
        contactRepository.deleteById(id);
    }

    public Page<contact> getMessages(int page, String keyword) {

        Pageable pageable = PageRequest.of(page, 10);

        if (keyword != null && !keyword.trim().isEmpty()) {
            return contactRepository
                    .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMessageContainingIgnoreCase(
                            keyword, keyword, keyword, pageable
                    );
        }

        return contactRepository.findAll(pageable);
    }

    public Page<contact> getMessages(int page, String keyword, Boolean isRead) {

        Pageable pageable = PageRequest.of(page, 10);

        return contactRepository.filterMessages(keyword, isRead, pageable);
    }

    public void markAsRead(int id) {

        contact msg = contactRepository.findById(id).orElse(null);

        if (msg != null && !msg.isRead()) {
            msg.setRead(true);
            contactRepository.save(msg);
        }
    }

    public long countByIsReadFalse() {
        return contactRepository.countByIsReadFalse();
    }
}