package br.com.slotshop.storeclient.service.impl;

import br.com.slotshop.server.enumeration.FreightType;
import br.com.slotshop.server.enumeration.OrderStatus;
import br.com.slotshop.server.enumeration.PaymentType;
import br.com.slotshop.server.model.Buy;
import br.com.slotshop.server.model.BuyFreight;
import br.com.slotshop.server.model.BuyPayment;
import br.com.slotshop.server.model.BuyProduct;
import br.com.slotshop.server.repository.data.BuyData;
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

    @Override
    public Buy buy(Checkout checkout, HttpSession session) {

        Cart cart = getCart(session);

        return buyData.save(Buy.builder()
                .payment(checkout.getPaymentType().equals(PaymentType.CREDITCARD) ? newBuyPaymentCreditCard(checkout, cart) : newBuyPaymentTicket(cart))
                .freight(newBuyFreight(cart))
                .adress(checkout.getUserAdress())
                .products(buyProductList(cart))
                .date(new Date())
                .total(getTotalByPayment(cart.getTotalCart(), cart.getPayment()))
                .status(OrderStatus.PAYMENTAPPROVED)
                .build());

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

    private List<BuyProduct> buyProductList(Cart cart){
        List<BuyProduct> buyProducts = new ArrayList<>();
        List<CartProduct> cartProducts = cart.getCartProducts();
        for (CartProduct cartProduct : cartProducts) {
            buyProducts.add(BuyProduct.builder()
                    .product(cartProduct.getProduct())
                    .amount(cartProduct.getAmount())
                    .total(cartProduct.getTotal())
                    .build());
        }
        return buyProducts;
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
