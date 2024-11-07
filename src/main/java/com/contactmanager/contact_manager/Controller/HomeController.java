package com.contactmanager.contact_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactmanager.contact_manager.dao.Contact_Repositery;
import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.User;
import com.contactmanager.contact_manager.entities.contact;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Contact_Repositery contact_Repositery;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    // @GetMapping("/test")
    // @ResponseBody
    // public String test() {
    //     User u = new User();
    //     u.setName("abc");
    //     u.setEmail("abc@gmail.com");
    //     contact c = new contact();
    //     u.getContacts().add(c);
    //     userRepository.save(u);
    //     return "Working";
    // }
    
    @RequestMapping("/")
    public String home(Model m) {
        m.addAttribute("Title", "Home - This is Home page");
        return "home";
    }
    

    @RequestMapping("/about")
    public String about(Model m) {
        m.addAttribute("Title", "About - This is About page");
        return "about";
    }

    @RequestMapping("/SingUp")
    public String SingUp(Model m) {
        m.addAttribute("Title", "SingUp - This is SingUp page");
        m.addAttribute("user", new User());
        return "SingUp";
    }

    @RequestMapping("/Login")
    public String Login(Model m) {
        m.addAttribute("Title", "Login - This is Login page");
        return "Login";
    }

    @RequestMapping("/ContactUs")
    public String ContactUs(Model m) {
        m.addAttribute("Title", "Contact-Us - This is Contact-Us page");
        m.addAttribute("contact", new contact());
        return "ContactUs";
    }

    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,Model model,@RequestParam("Confirm_Password") String Confirm_Password) {

        if (bindingResult.hasErrors()) {
            System.out.println("Errors are = "+bindingResult);
            return "SingUp";
        }

        if (!user.getPassword().equals(Confirm_Password)) {
            model.addAttribute("Confirm_Password_Message", true);
            return "SingUp";
        }

        if (userRepository.getUserByUserName(user.getEmail()) != null) {
            model.addAttribute("errorMessage", "This E-mail has account try to login");
            return "SingUp";
        }
        
        user.setRole("ROLE_USER");
        user.setEnables(true);
        user.setEmail(user.getEmail().toLowerCase());
        user.setIs_Alive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // System.out.println(user);
        User result = userRepository.save(user);
        model.addAttribute("user", result);
        model.addAttribute("successMessage", "Account created successfully!");
        return "SingUp";
    }
    

    @RequestMapping("/Login_Fail")
    public String Login_Fail(Model m) {
        m.addAttribute("Loginfail", "Wrong User Id Password");
        return "Login";
    }

    @RequestMapping("/do_message")
    public String message_from_user(@Valid @ModelAttribute("contact") contact contact, BindingResult bindingResult,Model m) {
        if (bindingResult.hasErrors()) {
            System.out.println("Errors are = " + bindingResult);
            return "ContactUs";
        }
        contact_Repositery.save(contact);
        m.addAttribute("message_from_user", true);
        return "ContactUs";
    }
    
}
