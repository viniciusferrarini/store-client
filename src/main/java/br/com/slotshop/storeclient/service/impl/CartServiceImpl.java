package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.server.service.imp.CrudServiceImpl;
import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.repository.CartRepository;
import br.com.slotshop.storeclient.service.CartService;
import br.com.slotshop.storeclient.util.RandomKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
public class CartServiceImpl extends CrudServiceImpl<Cart, Long> implements CartService {

    @Autowired private CartRepository cartRepository;

    @Override
    protected JpaRepository<Cart, Long> getRepository() {
        return cartRepository;
    }

    @Override
    public Cart addToCart(CartProduct cartProduct, String token) {
        Cart cart = cartRepository.findByToken(token);
        for (CartProduct product : cart.getCartProducts()) {
            if (cartProduct.getProduct().getId().equals(product.getProduct().getId())){
                product.setAmount(product.getAmount() + cartProduct.getAmount());
            }
        }
        cart.setSubTotalCart(cart.getCartProducts().stream().mapToDouble(CartProduct::getTotalValue).sum());
        cart.setTotalAmount(cart.getCartProducts().stream().mapToInt(CartProduct::getAmount).sum());
        return cart;
    }

    @Override
    public Cart newCart(CartProduct cartProduct) {

        Cart cart = new Cart();
        cart.setToken(RandomKeyUtil.randomString(25));
        cart.setTotalAmount(cartProduct.getAmount());
        cart.setSubTotalCart(cartProduct.getTotalValue());
        cart.setTotalCart(cartProduct.getTotalValue());
        cartProduct.setCart(cart);
        cart.setCartProducts(Arrays.asList(cartProduct));
        return cartRepository.save(cart);

    }

    @Override
    public Cart findByToken(String token) {
        return cartRepository.findByToken(token);
    }
}
