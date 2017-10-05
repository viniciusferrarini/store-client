package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.server.service.imp.CrudServiceImpl;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.repository.CartProductRepository;
import br.com.slotshop.storeclient.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CartProductServiceImpl extends CrudServiceImpl<CartProduct, Long> implements CartProductService {

    @Autowired private CartProductRepository cartProductRepository;

    @Override
    protected JpaRepository<CartProduct, Long> getRepository() {
        return cartProductRepository;
    }

}
