package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.repository.CartRepository;
import br.com.slotshop.storeclient.service.CartProductService;
import br.com.slotshop.storeclient.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired private CartRepository cartRepository;

    @Autowired private CartProductService cartProductService;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findOne(String id) {
        return cartRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        cartRepository.delete(id);
    }

    @Override
    public Cart addToCart(CartProduct cartProduct, String id) {
        Cart cart = cartRepository.findOne(id);
        for (CartProduct product : cart.getCartProducts()) {
            if (Objects.equals(cartProduct.getProduct(), product.getProduct())){
                product.setAmount(product.getAmount() + cartProduct.getAmount());
            }
        }
        cart.setSubTotalCart(cart.getCartProducts().stream().mapToDouble(CartProduct::getTotalValue).sum());
        cart.setTotalAmount(cart.getCartProducts().stream().mapToInt(CartProduct::getAmount).sum());
        return cart;
    }

    @Override
    public Cart newCart(CartProduct cartProduct) {
        return cartRepository.insert(Cart.builder()
                .totalAmount(cartProduct.getAmount())
                .subTotalCart(cartProduct.getTotalValue())
                .totalCart(cartProduct.getTotalValue())
                .cartProducts(Collections.singletonList(cartProductService.save(cartProduct)))
                .build());
    }

}
