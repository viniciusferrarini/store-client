package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.Product;
import br.com.slotshop.storeclient.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired private SearchService searchService;

    @PostMapping("/findBySearchTerm")
    public ModelAndView findBySearchTerm(@ModelAttribute("search") String search){
        Page<Product> products = searchService.findBySearchTerm(search);
        ModelAndView modelAndView = new ModelAndView("/search");
        modelAndView.addObject("search", search);
        return getPageable(modelAndView, products);
    }

    @GetMapping("/findBySearchTermAndPage")
    public ModelAndView findBySearchTermAndPage(@RequestParam("search") String search, @RequestParam("page") Integer page){
        Page<Product> products = searchService.findBySearchTermAndPage(search, page - 1);
        ModelAndView modelAndView = new ModelAndView("/search");
        modelAndView.addObject("search", search);
        return getPageable(modelAndView, products);
    }

    private ModelAndView getPageable(ModelAndView modelAndView, Page<Product> page){
        modelAndView.addObject("pages", getSetPages(page.getTotalPages()));
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());
        modelAndView.addObject("page", page.getNumber() + 1);
        modelAndView.addObject("products", page.getContent());
        return modelAndView;
    }

    private Set<Integer> getSetPages(int totalPages){
        Set<Integer> pages = new HashSet<>();
        for (int i = 0; i < totalPages; i++) {
             pages.add(i+1);

        }
        return pages;
    }

}
