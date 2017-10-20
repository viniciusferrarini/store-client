package br.com.slotshop.storeclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired private UserDetailsService userDetailsService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/login");
    }



}
