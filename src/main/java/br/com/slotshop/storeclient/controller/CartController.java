package br.com.slotshop.storeclient.controller;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired private CartService cartService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/cart");
    }

    @GetMapping("/token/{token}")
    public @ResponseBody Cart findByToken(@PathVariable("token") String token){
        return cartService.findByToken(token);
    }

    @PostMapping("/token")
    public @ResponseBody Cart addToCart(@RequestBody CartProduct cartProduct, @RequestParam(value = "token", required = false) String token) {
        if(!token.isEmpty() && token.length() == 25) {
            return cartService.addToCart(cartProduct, token);
        }
        return null;
    }

    @PostMapping
    public @ResponseBody Cart newCart(@RequestBody CartProduct cartProduct){
        return cartService.newCart(cartProduct);
    }

}
