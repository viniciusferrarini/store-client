package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.server.model.Product;
import br.com.slotshop.server.service.ProductService;
import br.com.slotshop.storeclient.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired private ProductService productService;

    @Override
    public Page<Product> findBySearchTerm(String search) {
        return productService.findBySearchTerm(search);
    }

    @Override
    public Page<Product> findBySearchTermAndPage(String search, Integer page) {
        return productService.findBySearchTermAndPage(search, page);
    }
}
