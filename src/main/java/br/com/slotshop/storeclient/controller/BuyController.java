package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.Buy;
import br.com.slotshop.server.service.BuyService;
import br.com.slotshop.server.service.UserService;
import br.com.slotshop.storeclient.model.dto.Checkout;
import br.com.slotshop.storeclient.service.BuyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("buy")
public class BuyController {

    @Autowired private BuyClientService buyClientService;

    @Autowired private BuyService buyService;

    @Autowired private UserService userService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/buy");
    }

    @PostMapping
    public @ResponseBody Buy buy(@Valid @RequestBody Checkout checkout, HttpSession session){
        return buyClientService.buy(checkout, session);
    }

    @GetMapping("/findAll")
    public @ResponseBody List<Buy> findAllByUser(){
        return buyService.findByUser(userService.getLoggedUser());
    }

}
