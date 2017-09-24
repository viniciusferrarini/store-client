package br.com.slotshop.storeclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller("/product")
public class ProductController {

    @GetMapping("/product/{id}/{name}")
    public ModelAndView get(@PathVariable("id") Long id, @PathVariable("name") String name){
        ModelAndView modelAndView = new ModelAndView("/product");
        modelAndView.addObject("id", id);
        modelAndView.addObject("title", name);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public @ResponseBody String getProduct(@PathVariable("id") Long id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:7990/product/" + id, String.class);
    }

}
