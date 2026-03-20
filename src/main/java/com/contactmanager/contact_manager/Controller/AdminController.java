package com.contactmanager.contact_manager.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.contactmanager.contact_manager.dao.Contact_Repositery;
import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.User;
import com.contactmanager.contact_manager.entities.contact;

import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Contact_Repositery contact_Repositery;

    @ModelAttribute
    public void addCommonData(Model m, Principal principal) {
        String username = principal.getName();
        User admin = userRepository.getUserByUserName(username);
        List<User> users = userRepository.getAllActiveUsers();
        this.userRepository.save(admin);
        // this.userRepository.save(users);
        m.addAttribute("admin", admin);
        m.addAttribute("users", users);
    }

    @ModelAttribute
    public void getCommondataForMessage(Model m) {
        List<contact> con = contact_Repositery.findAll();
        m.addAttribute("contact", con);
    }

    @ModelAttribute
    public void setTotalUSers(Model m) {
        int U_count = (int) userRepository.getAllActiveUsers().size();
        int M_count = (int) contact_Repositery.count();
        m.addAttribute("Total_Users", U_count);
        m.addAttribute("Total_Messages", M_count);
    }

    @RequestMapping("/index")
    public String AdminIndex(Model m) {
        m.addAttribute("Title", "Dashbord");
        return "admin/index";
    }

    @RequestMapping("/demo")
    public String requestMethodName() {
        return "admin/demo";
    }

    @RequestMapping("/user-list")
    public String User_Management(Model m) {
        m.addAttribute("Title", "User Management Page");
        return "admin/user_management";
    }

    @RequestMapping("/show-messages")
    public String requestMethodName(Model m, Principal principal) {
        m.addAttribute("Title", "Show Messages Page");
        // List<contact> con = contact_Repositery.findAll();
        // m.addAttribute("contact", con);
        return "admin/messages";
    }

    @GetMapping("/get-message")
    @ResponseBody
    public String getMessage(@RequestParam("id") int messageId) {

        return contact_Repositery.getMessageByCid(messageId);
    }


    @GetMapping("/delete-message")
    @ResponseBody
    public String deleteMessage(@RequestParam("id") int id) {

        contact_Repositery.deleteById(id);

        return "success";
    }

    @RequestMapping("/see-message")
    public String seeMessage(@RequestParam("m_id") int messageId, Model m) {

        // Get message directly from DB
        String message = contact_Repositery.getMessageByCid(messageId);

        // Send to frontend
        m.addAttribute("message", message);

        // ALSO send full list again (IMPORTANT)
        List<contact> contactList = contact_Repositery.findAll();
        m.addAttribute("contact", contactList);

        return "admin/messages";
    }


    // @PostMapping("/make-user-dead")

    @RequestMapping("/make-user-dead")
    public String postMethodName(@RequestParam("a_id") int userId, Model model) {
        try {
            int updatedRows = this.userRepository.updateUserById(userId);

            if (updatedRows != 0) {
                model.addAttribute("User_Deleted", true);
            }
        } catch (Exception e) {
            return "admin/user_management";
        }
        return "admin/user_management";
    }


}
