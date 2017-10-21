package br.com.slotshop.storeclient.controller;

import br.com.slotshop.storeclient.model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @GetMapping
    public ModelAndView get(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null && (cart.getCartProducts() != null || !cart.getCartProducts().isEmpty())) {
            return new ModelAndView("/checkout");
        }
        return new ModelAndView("/cart");
    }

}
