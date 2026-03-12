package com.contactmanager.contact_manager.Controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactmanager.contact_manager.dao.UserRepository;
import com.contactmanager.contact_manager.entities.EmailReqest;
import com.contactmanager.contact_manager.service.EmailService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/email")
public class EmailController {

    int otp;
    HashMap<String,Integer> hashMap = new HashMap<>();

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/sendmail")
    public String SendMailToUser(@ModelAttribute("forgotemail") EmailReqest eReqest,Model m,RedirectAttributes redirectAttributes) {

        if(userRepository.getUserByUserName(eReqest.getForm()) != null){
        Random random = new Random(1000);
        this.otp = random.nextInt(9999999);
        System.out.println(otp);

        // System.out.println("Client Email id == "+eReqest.getForm());
        boolean b = this.emailService.sendEmail("Reset password OTP","Your OTP is "+otp+"\nDon't Share this OTP With others" , eReqest.getForm());
        // System.out.println("Not Working");
        if (b) {
            m.addAttribute("forgotemail", eReqest);
            m.addAttribute("otp_generated",true);
            hashMap.put(eReqest.getForm(), otp);
            return "checkOTP";    
        }else{
            return "redirect:/Login";
        }
    }else{
        
        // m.addAttribute("User_Not_Exist", true);
        redirectAttributes.addFlashAttribute("User_Not_Exist", true);
        // session.getAttribute("",);
        return "redirect:/forgot_password";
    }

        
    }

    @RequestMapping("/CheckOtpDemo")
    public String CheckOtp(@RequestParam("OTP") String OTP,Model m) {
        int check_otp = Integer.parseInt(OTP);
        m.addAttribute("forgotemail", new EmailReqest());
        if (otp == check_otp) {
            // m.addAllAttributes(forgotemail,new)
            m.addAttribute("Correct_otp", true);
            return "checkOTP";
        }else{
            m.addAttribute("InCorrect_otp", true);
            return "checkOTP";
        }
        // return "checkOTP";
    }
    
}
