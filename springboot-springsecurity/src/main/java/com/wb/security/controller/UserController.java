package com.wb.security.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasAuthority('R_ADMIN')")
    @RequestMapping("/admin")
    public String admin(Model model, String tt) {
        return "admin";
    }

    @RequestMapping("/hello")
    public String hello(Model model, String tt) {
        return "hello";
    }

}

