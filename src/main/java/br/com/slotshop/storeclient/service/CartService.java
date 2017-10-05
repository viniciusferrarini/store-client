package br.com.slotshop.storeclient.service;

import br.com.slotshop.server.service.CrudService;
import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;

public interface CartService extends CrudService<Cart, Long> {

    Cart addToCart(CartProduct cartProduct, String token);

    Cart newCart(CartProduct cartProduct);

    Cart findByToken(String token);
}
