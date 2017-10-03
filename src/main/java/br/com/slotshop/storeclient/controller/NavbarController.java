package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.dto.Navbar;
import br.com.slotshop.server.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("navbar")
public class NavbarController {

    @Autowired private SubCategoryService subCategoryService;

    @Value("${server.url}")
    private String server;

    @GetMapping
    public @ResponseBody List<Navbar> getNavbar(){
        return subCategoryService.getNavbar();
    }

}