package br.com.slotshop.storeclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired private RestTemplate restTemplate;

    @Value("${server.url}")
    private String server;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("/cart");
    }

    @GetMapping("/{id}")
    public @ResponseBody String getProduct(@PathVariable("id") Long id){
        return restTemplate.getForObject(server + "/product/" + id, String.class);
    }

}
