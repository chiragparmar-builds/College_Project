package com.contactmanager.contact_manager.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public String requestMethodName() {
        return "/error/404";
    }
}