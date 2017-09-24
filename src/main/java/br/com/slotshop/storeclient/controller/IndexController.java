package br.com.slotshop.storeclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/findFirst10")
    public String getFirst10Products(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:7990/product/findFirst10", String.class);
    }

    @ResponseBody
    @RequestMapping("/picture/{picture}")
    public String getPicture(@PathVariable("picture") String picture){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:7990/gallery/picture/" + picture, String.class);
    }

}