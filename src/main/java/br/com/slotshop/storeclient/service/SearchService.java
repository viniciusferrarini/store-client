package br.com.slotshop.storeclient.service;

import br.com.slotshop.server.model.Product;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<Product> findBySearchTerm(String search);

    Page<Product> findBySearchTermAndPage(String search, Integer page);
}
