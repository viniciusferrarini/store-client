package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.Buy;
import br.com.slotshop.storeclient.model.dto.Checkout;
import br.com.slotshop.storeclient.service.BuyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("buy")
public class BuyController {

    @Autowired private BuyClientService buyClientService;

    @PostMapping
    private @ResponseBody Buy buy(@Valid @RequestBody Checkout checkout, HttpSession session){
        return buyClientService.buy(checkout, session);
    }


}
