package br.com.slotshop.storeclient.controller;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.model.xml.CResultadoType;
import br.com.slotshop.storeclient.service.CartService;
import br.com.slotshop.storeclient.service.PriceTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
@Scope("session")
public class CartController {

    @Autowired private CartService cartService;

    @Autowired private PriceTimeService priceTimeService;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/cart");
    }

    @PostMapping
    public @ResponseBody Cart addToCart(@RequestBody CartProduct cartProduct, HttpSession session) {
        return cartService.addToCart(cartProduct, session);
    }

    @GetMapping("/amountTotalCart")
    public @ResponseBody Integer amountTotalCart(HttpSession session){
        return cartService.getAmountTotalCart(session);
    }

    @GetMapping("/getCart")
    public @ResponseBody Cart getCart(HttpSession session){
        return cartService.getCart(session);
    }

    @PostMapping("/removeToCart")
    public @ResponseBody Cart removeToCart(@RequestBody CartProduct cartProduct, HttpSession session){
        return cartService.removeToCart(cartProduct, session);
    }

    @GetMapping("/calculatePriceTime/{zip}")
    private @ResponseBody CResultadoType calculatePriceTime(@PathVariable("zip") String zipDestiny){
        return priceTimeService.getPriceTime(zipDestiny);
    }

}
