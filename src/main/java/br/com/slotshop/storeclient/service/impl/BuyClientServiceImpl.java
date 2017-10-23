package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.server.enumeration.FreightType;
import br.com.slotshop.server.enumeration.OrderStatus;
import br.com.slotshop.server.enumeration.PaymentType;
import br.com.slotshop.server.model.*;
import br.com.slotshop.server.repository.data.BuyData;
import br.com.slotshop.server.service.ProductService;
import br.com.slotshop.server.service.UserService;
import br.com.slotshop.storeclient.model.Cart;
import br.com.slotshop.storeclient.model.CartProduct;
import br.com.slotshop.storeclient.model.dto.Checkout;
import br.com.slotshop.storeclient.model.xml.CServicoType;
import br.com.slotshop.storeclient.service.BuyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BuyClientServiceImpl implements BuyClientService {

    @Autowired private BuyData buyData;

    @Autowired private UserService userService;

    @Autowired private ProductService productService;

    @Override
    public Buy buy(Checkout checkout, HttpSession session) {

        Cart cart = getCart(session);
        session.removeAttribute("cart");

        Buy buy = new Buy();
        buy.setPayment(checkout.getPaymentType().equals(PaymentType.CREDITCARD) ? newBuyPaymentCreditCard(checkout, cart) : newBuyPaymentTicket(cart));
        buy.setFreight(newBuyFreight(cart));
        buy.setAdress(checkout.getUserAdress());
        buy.setProducts(buyProductList(cart, buy));
        buy.setUser(userService.getLoggedUser());
        buy.setDate(new Date());
        buy.setTotal(getTotalByPayment(cart.getTotalCart(), cart.getPayment()));
        buy.setStatus(OrderStatus.PAYMENTAPPROVED);

        return buyData.save(buy);

    }

    private Double getTotalByPayment(Double total, PaymentType paymentType){
        if (paymentType.equals(PaymentType.CREDITCARD)){
            return total;
        }
        return total - ((total * 10) / 100);
    }

    private BuyPayment newBuyPaymentTicket(Cart cart){
        return BuyPayment.builder()
                .paymentType(PaymentType.TICKET)
                .discounts((cart.getSubTotalCart() * 10) /100)
                .build();
    }

    private BuyPayment newBuyPaymentCreditCard(Checkout checkout, Cart cart){
        return BuyPayment.builder()
                .paymentType(PaymentType.CREDITCARD)
                .discounts(0.0)
                .amountParcel(checkout.getAmountParcel())
                .totalParcel(cart.getTotalCart() / checkout.getAmountParcel())
                .build();
    }

    private BuyFreight newBuyFreight(Cart cart){
        CServicoType freight = cart.getFreight();
        return BuyFreight.builder()
                .freightType(verificationFreight(freight.getCodigo()))
                .value(freight.getValorDouble())
                .deliveryTime(Integer.parseInt(freight.getPrazoEntrega()))
                .build();
    }

    private List<BuyProduct> buyProductList(Cart cart, Buy buy){
        List<BuyProduct> buyProducts = new ArrayList<>();
        List<CartProduct> cartProducts = cart.getCartProducts();
        for (CartProduct cartProduct : cartProducts) {
            Product product = refreshProductAmount(cartProduct);
            buyProducts.add(BuyProduct.builder()
                    .product(product)
                    .amount(cartProduct.getAmount())
                    .buy(buy)
                    .total(product.getValue() * cartProduct.getAmount())
                    .build());
        }
        return buyProducts;
    }

    private Product refreshProductAmount(CartProduct cartProduct) {
        Product product = cartProduct.getProduct();
        product.setAmount(product.getAmount() - cartProduct.getAmount());
        productService.save(product);
        return product;
    }

    private FreightType verificationFreight(String code){
        if(Integer.parseInt(code) == 4014){
            return FreightType.SEDEX;
        }
        return FreightType.PAC;
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            return cart;
        }
        return null;
    }

}
