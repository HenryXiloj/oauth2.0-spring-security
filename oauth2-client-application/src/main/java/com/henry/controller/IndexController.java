package com.henry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    @GetMapping("/")
    public String home1() {
        System.out.println("redirect OAuth2 Login");
        return "redirect:/oauth2/authorization/auth";
    }

    @GetMapping("/index")
    public String getLoginPage() {
        return "index";
    }

}
