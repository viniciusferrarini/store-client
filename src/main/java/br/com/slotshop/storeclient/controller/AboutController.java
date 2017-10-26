package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.enumeration.OrderStatus;
import br.com.slotshop.server.model.Buy;
import br.com.slotshop.server.model.Product;
import br.com.slotshop.server.model.User;
import br.com.slotshop.server.service.BuyService;
import br.com.slotshop.server.service.ProductService;
import br.com.slotshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("about")
public class AboutController {

    @Autowired private ProductService productService;

    @Autowired private BuyService buyService;

    @Autowired private UserService userService;

    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView = new ModelAndView("/about");
        modelAndView.addObject("productsAmount", totalProducts());
        modelAndView.addObject("sellsAmount", totalSells());
        modelAndView.addObject("usersAmount", totalUsers());
        return modelAndView;
    }

    private long totalUsers() {
        return userService.findAll().stream().mapToLong(User::getId).count();
    }

    private long totalProducts(){
        return productService.findAll().stream().filter(product -> product.getAmount() > 0).mapToLong(Product::getId).count();
    }

    private long totalSells(){
        return buyService.findAll().stream().filter(buy -> buy.getStatus().equals(OrderStatus.COMPLETED)).mapToLong(Buy::getId).count();
    }

}
