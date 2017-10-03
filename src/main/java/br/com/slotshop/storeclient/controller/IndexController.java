package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.Product;
import br.com.slotshop.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    @Autowired private ProductService productService;

    @Autowired private RestTemplate restTemplate;

    @Value("${server.url}")
    private String server;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/findFirst10")
    public @ResponseBody Page<Product> getFirst10Products(){
        return productService.findFirst10();
    }

    @ResponseBody
    @RequestMapping("/picture/{picture}")
    public String getPicture(@PathVariable("picture") String picture){
        return restTemplate.getForObject(server + "/gallery/picture/" + picture, String.class);
    }

}