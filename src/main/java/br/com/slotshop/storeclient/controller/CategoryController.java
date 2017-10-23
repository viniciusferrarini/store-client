package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.model.Product;
import br.com.slotshop.server.model.SubCategory;
import br.com.slotshop.server.service.CategoryService;
import br.com.slotshop.server.service.ProductService;
import br.com.slotshop.server.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired private ProductService productService;

    @Autowired private SubCategoryService subCategoryService;

    @GetMapping
    public ModelAndView get(@RequestParam("id") Long id, @RequestParam("page") Integer page){
        SubCategory subCategory = subCategoryService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("/category");
        modelAndView.addObject("categoryId", id);
        modelAndView.addObject("category", subCategory.getName());
        getPageable(modelAndView, productService.findByCategory(subCategory.getCategory(), page));
        return modelAndView;
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
