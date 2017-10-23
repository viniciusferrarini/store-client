package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/buyDetails")
public class BuyDetailsController {

    @Autowired private BuyService buyService;

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/buyDetails");
        return modelAndView.addObject("buy", buyService.findOne(id));
    }

}
