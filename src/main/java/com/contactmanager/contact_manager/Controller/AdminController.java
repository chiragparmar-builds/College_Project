package com.contactmanager.contact_manager.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactmanager.contact_manager.dao.Contact_Repositery;
import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.User;
import com.contactmanager.contact_manager.entities.contact;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Contact_Repositery contact_Repositery;

    @ModelAttribute
    public void addCommonData(Model m, Principal principal){
        String username = principal.getName();
        User admin = userRepository.getUserByUserName(username);
        List<User>users = userRepository.getAllActiveUsers();
        this.userRepository.save(admin);
        // this.userRepository.save(users);
        m.addAttribute("admin", admin);
        m.addAttribute("users", users);
    }

    @ModelAttribute
    public void getCommondataForMessage(Model m){
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
        return "/admin/index";
    }

    @RequestMapping("/demo")
    public String requestMethodName() {
        return "/admin/demo";
    }

    @RequestMapping("/user-list")
    public String User_Management(Model m) {
        m.addAttribute("Title", "User Management Page");
        return "/admin/User_Management";
    }

    @RequestMapping("/show-messages")
    public String requestMethodName(Model m, Principal principal) {
        m.addAttribute("Title", "Show Messages Page");
        // List<contact> con = contact_Repositery.findAll();
        // m.addAttribute("contact", con);
        return "/admin/Messages";
    }
    

     @RequestMapping("/see-message")
     public String requestMethodName(@RequestParam("m_id") int messageId,@ModelAttribute("contact") List<contact> cont,Model m) {
        // System.out.println(messageId);
        String message = null;

        String v = contact_Repositery.getMessageByCid(messageId);
        System.out.println(v);
        
        // for (contact c1 : cont) {
        //     if (messageId == c1.getCid()) {
        //         message = c1.getMessage();
        //         break;
        //     }
        // }

        message = cont.stream().filter(cont1 -> cont1.getCid() == messageId)
                                .map(contact::getMessage).findFirst().orElse(null);

        // m.addAttribute("message", v);
        m.addAttribute("message", message);
         return "/admin/Messages";
     }


        // @PostMapping("/make-user-dead")

        @RequestMapping("/make-user-dead")
        public String postMethodName(@RequestParam("a_id") int userId, Model model) {
            try {
            int updatedRows = this.userRepository.updateUserById(userId);

            if (updatedRows != 0) {
                model.addAttribute("User_Deleted", true);
            } 
        }catch (Exception e){
            return "/admin/User_Management";
        }
            return "/admin/User_Management";
        }
     
    
    
}
