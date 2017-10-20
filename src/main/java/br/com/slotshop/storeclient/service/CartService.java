package br.com.slotshop.storeclient.service;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;

import javax.servlet.http.HttpSession;

public interface CartService {

    Cart addToCart(CartProduct cartProduct, HttpSession session);

    Integer getAmountTotalCart(HttpSession session);

    Cart getCart(HttpSession session);

    Cart removeToCart(CartProduct cartProduct, HttpSession session);

    Cart changeFreight(Cart cart, HttpSession session);

    Cart changeAmount(Cart cart, HttpSession session);

    Cart changePayment(Cart cart, HttpSession session);

}
