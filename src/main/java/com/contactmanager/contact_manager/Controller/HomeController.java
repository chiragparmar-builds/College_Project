package com.contactmanager.contact_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactmanager.contact_manager.dao.Contact_Repositery;
import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.EmailReqest;
import com.contactmanager.contact_manager.entities.User;
import com.contactmanager.contact_manager.entities.contact;

import jakarta.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Contact_Repositery contact_Repositery;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    @RequestMapping("/forgot_password")
    public String requestMethodName(Model m) {
        m.addAttribute("forgotemail", new EmailReqest());
        try {
            boolean result = (boolean) m.getAttribute("User_Not_Exist");
            m.addAttribute("User_Not_Exist", result);
        } catch (Exception e) {

        }
        return "forgotpassword";
    }


    @RequestMapping("/SingUp")
    public String SingUp(Model m) {
        m.addAttribute("Title", "SingUp - This is SingUp page");
        m.addAttribute("user", new User());
        return "singup";
    }

    @RequestMapping("/Login")
    public String Login(Model m) {
        m.addAttribute("Title", "Login - This is Login page");
        return "login";
    }

    @RequestMapping("/ContactUs")
    public String ContactUs(Model m) {
        m.addAttribute("Title", "Contact-Us - This is Contact-Us page");
        m.addAttribute("contact", new contact());
        return "contactus";
    }

    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, @RequestParam("Confirm_Password") String Confirm_Password) {

        if (bindingResult.hasErrors()) {
            System.out.println("Errors are = " + bindingResult);
            return "singup";
        }

        if (!user.getPassword().equals(Confirm_Password)) {
            model.addAttribute("Confirm_Password_Message", true);
            return "singup";
        }

        if (userRepository.getUserByUserName(user.getEmail()) != null) {
            model.addAttribute("errorMessage", "This E-mail has account try to login");
            return "singup";
        }

        user.setRole("ROLE_USER");
        user.setEnables(true);
        user.setEmail(user.getEmail().toLowerCase());
        user.setIs_Alive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        model.addAttribute("user", result);
        model.addAttribute("successMessage", "Account created successfully!");
        return "singup";
    }


    @RequestMapping("/Login_Fail")
    public String Login_Fail(Model m) {
        m.addAttribute("Loginfail", "Wrong User Id Password");
        return "login";
    }

    @PostMapping("/do_message")
    public String message_from_user(@Valid @ModelAttribute("contact") contact contact, BindingResult bindingResult, Model m, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println("Errors are = " + bindingResult);
            m.addAttribute("Title", "Contact-Us - Errors found");
            return "contactus";
        }
        contact_Repositery.save(contact);
        redirectAttributes.addFlashAttribute("message_from_user", true);
        return "redirect:/ContactUs";
    }

}
