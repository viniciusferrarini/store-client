package br.com.slotshop.storeclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("buyConfirmation")
public class BuyConfirmationController {

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/buyconfirmation");
    }

}
