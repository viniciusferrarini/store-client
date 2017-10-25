package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.server.enumeration.PaymentType;
import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.model.xml.CServicoType;
import br.com.slotshop.storeclient.service.CartService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
            List<CartProduct> cartProducts = new ArrayList<>(cart.getCartProducts());
            if (cartProducts.size() > 0 && checkCartProductExists(cartProduct, cart)) {
                for (CartProduct product : cartProducts) {
                    if (cartProduct.getProduct().getId().equals(product.getProduct().getId())) {
                        product.setAmount(product.getAmount() + cartProduct.getAmount());
                        product.setTotal(product.getProduct().getValue() * product.getAmount());
                    }
                }
            }else{
                cartProducts.add(cartProduct);
            }
            double subTotalCart = cartProducts.stream().mapToDouble(CartProduct::getTotalValue).sum();
            cart.setSubTotalCart(subTotalCart);
            cart.setTotalAmount(cartProducts.stream().mapToInt(CartProduct::getAmount).sum());
            cart.setTotalCart(subTotalCart + (cart.getFreight().getValorDouble() != null ? cart.getFreight().getValorDouble() : 0));
            cart.setCartProducts(cartProducts);
            return cart;
        }
        return newCart(cartProduct, session);
    }

    @Override
    public Cart removeToCart(CartProduct cartProduct, HttpSession session) {
        Cart cart = getCart(session);

        boolean present = cart.getCartProducts()
                .stream()
                .anyMatch(cartProd -> !cartProd.getId().equals(cartProduct.getId()));

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

    @Override
    public Cart changeFreight(Cart cart, HttpSession session) {
        cart.setTotalCart(cart.getSubTotalCart() + cart.getFreight().getValorDouble());
        saveCart(cart, session);
        return cart;
    }

    @Override
    public Cart changeAmount(Cart cart, HttpSession session) {
        int totalCartAmount = cart.getCartProducts().stream().mapToInt(CartProduct::getAmount).sum();
        double subTotalCart = cart.getCartProducts().stream().mapToDouble(CartProduct::getTotalValue).sum();
        cart.setTotalAmount(totalCartAmount);
        cart.setSubTotalCart(subTotalCart);
        cart.setTotalCart(subTotalCart + cart.getFreight().getValorDouble());
        saveCart(cart, session);
        return cart;
    }

    @Override
    public Cart changePayment(Cart cart, HttpSession session) {
        if (cart.getPayment().equals(PaymentType.TICKET)) {
            cart.setPaymentOff((cart.getSubTotalCart() * 10) / 100);
        } else {
            cart.setPaymentOff(0.0);
        }
        saveCart(cart, session);
        return cart;
    }

    private void saveCart(Cart cart, HttpSession session) {
        session.setAttribute("cart", cart);
    }

    private Cart newCart(CartProduct cartProduct, HttpSession session) {
        Cart cart = new Cart();
        cart.setTotalAmount(cartProduct.getAmount());
        cart.setSubTotalCart(cartProduct.getTotalValue());
        cart.setTotalCart(cartProduct.getTotalValue());
        cart.setFreight(new CServicoType());
        cart.setCartProducts(Collections.singletonList(cartProduct));
        session.setAttribute("cart", cart);
        return cart;
    }

    private Boolean checkCartProductExists(CartProduct cartProduct, Cart cart){
        for (CartProduct product : cart.getCartProducts()) {
            if (cartProduct.getProduct().getId().equals(product.getProduct().getId())) {
                return true;
            }
        }
        return false;
    }

}
