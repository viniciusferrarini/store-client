package br.com.slotshop.storeclient.service;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;

public interface CartService {

    Cart save(Cart cart);

    Cart findOne(String id);

    void delete(String id);

    Cart addToCart(CartProduct cartProduct, String id);

    Cart newCart(CartProduct cartProduct);
}
