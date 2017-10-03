package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.Product;
import br.com.slotshop.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired private ProductService productService;

    @Value("${server.url}")
    private String server;

    @GetMapping("/{id}/{name}")
    public ModelAndView get(@PathVariable("id") Long id, @PathVariable("name") String name){
        ModelAndView modelAndView = new ModelAndView("/product");
        modelAndView.addObject("id", id);
        modelAndView.addObject("title", name);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public @ResponseBody Product getProduct(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

}
