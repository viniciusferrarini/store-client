package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.repository.CartProductRepository;
import br.com.slotshop.storeclient.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired private CartProductRepository cartProductRepository;

    @Override
    public CartProduct save(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }
}
