package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.User;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/myinfo")
public class MyInfoController {

    @Autowired private UserService userService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/myinfo");
    }

    @GetMapping("/getInfo")
    public @ResponseBody User getInfo(){
        return userService.getLoggedUser();
    }

    @PostMapping
    public @ResponseBody User saveInfo(@Valid @RequestBody User user){
        return userService.save(user);
    }

}
