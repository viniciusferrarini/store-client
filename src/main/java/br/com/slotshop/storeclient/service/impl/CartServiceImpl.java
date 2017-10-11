package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.service.CartService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Transactional
@Scope("session")
public class CartServiceImpl implements CartService {

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            return cart;
        }
        return null;
    }

    @Override
    public Integer getAmountTotalCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            return cart.getTotalAmount();
        }
        return 0;
    }

    @Override
    public Cart addToCart(CartProduct cartProduct, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            if (cart.getCartProducts().size() > 0) {
                for (CartProduct product : cart.getCartProducts()) {
                    if (cartProduct.getProduct().getId().equals(product.getProduct().getId())) {
                        product.setAmount(product.getAmount() + cartProduct.getAmount());
                    }
                }
            }else{
                cart.getCartProducts().add(cartProduct);
            }
            cart.setSubTotalCart(cart.getCartProducts().stream().mapToDouble(CartProduct::getTotalValue).sum());
            cart.setTotalAmount(cart.getCartProducts().stream().mapToInt(CartProduct::getAmount).sum());
            return cart;
        }
        return newCart(cartProduct, session);
    }

    @Override
    public Cart removeToCart(CartProduct cartProduct, HttpSession session) {
        Cart cart = getCart(session);

        boolean present = cart.getCartProducts()
                .stream()
                .filter(cartProd -> !cartProd.getId().equals(cartProduct.getId())).findFirst().isPresent();

        if (present && cart.getCartProducts().size() > 1) {
            cart.setTotalAmount(cart.getTotalAmount() - cartProduct.getAmount());
            cart.setTotalCart(cart.getTotalCart() - cartProduct.getTotalValue());
            cart.setSubTotalCart(cart.getSubTotalCart() - cartProduct.getTotalValue());
            cart.setCartProducts(cart.getCartProducts()
                    .stream()
                    .filter(cartProd -> !cartProd.getId().equals(cartProduct.getId()))
                    .collect(Collectors.toList()));
            return cart;
        }
        session.removeAttribute("cart");
        return null;
    }

    private Cart newCart(CartProduct cartProduct, HttpSession session) {
        Cart cart = new Cart();
        cart.setTotalAmount(cartProduct.getAmount());
        cart.setSubTotalCart(cartProduct.getTotalValue());
        cart.setTotalCart(cartProduct.getTotalValue());
        cart.setCartProducts(Collections.singletonList(cartProduct));
        session.setAttribute("cart", cart);
        return cart;
    }

}
