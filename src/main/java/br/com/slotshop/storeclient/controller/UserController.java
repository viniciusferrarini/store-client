package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.User;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    public @ResponseBody User loggedUser(){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser != null){
            return loggedUser;
        }
        return null;
    }

}
