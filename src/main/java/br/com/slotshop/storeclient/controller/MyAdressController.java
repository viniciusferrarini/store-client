package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.UserAdress;
import br.com.slotshop.server.service.UserAdressService;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/myadress")
public class MyAdressController {

    @Autowired private UserAdressService userAdressService;

    @Autowired private UserService userService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/myadress");
    }

    @GetMapping("/getAdressList")
    public @ResponseBody List<UserAdress> getAdressList(){
        return userAdressService.findByUser(userService.getLoggedUser());
    }

}
