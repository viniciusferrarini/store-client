package br.com.slotshop.storeclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller("/navbar")
public class NavbarController {

    @GetMapping
    public @ResponseBody String getNav(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:7990/subCategory";
        return restTemplate.getForObject(url, String.class);
    }

}
