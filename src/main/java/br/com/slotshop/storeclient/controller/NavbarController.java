package br.com.slotshop.storeclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/navbar")
public class NavbarController {

    @GetMapping
    public @ResponseBody String getNavbar(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:7990/subCategory/navbar", String.class);
    }

}