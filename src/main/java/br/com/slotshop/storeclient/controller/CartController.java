package br.com.slotshop.storeclient.controller;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/cart");
    }

    @PostMapping
    public @ResponseBody Cart addToCart(@RequestBody CartProduct cartProduct, @RequestParam("id") String id){
        if(id != null) {
            return cartService.newCart(cartProduct);
        }
        return cartService.addToCart(cartProduct, id);
    }

}
