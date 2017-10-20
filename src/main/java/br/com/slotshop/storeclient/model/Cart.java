package br.com.slotshop.storeclient.model;

import br.com.slotshop.server.enumeration.PaymentType;
import br.com.slotshop.server.util.DoubleUtil;
import br.com.slotshop.storeclient.model.xml.CServicoType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private PaymentType payment = PaymentType.NOTSELECTED;

    private Double paymentOff;

    private Double subTotalCart;

    private String zipCode;

    private CServicoType freight = new CServicoType();

    private Double totalCart;

    private Integer totalAmount;

    private List<CartProduct> cartProducts;

    public String getSubTotalCartFormatted(){
        return DoubleUtil.formatRealWithSimbol(this.subTotalCart);
    }

    public String getTotalCartFormatted(){
        return DoubleUtil.formatRealWithSimbol(this.totalCart);
    }

    public String getPaymentOffFormatted(){
        if (this.paymentOff != null) {
            return DoubleUtil.formatRealWithSimbol(this.paymentOff);
        }else{
            return DoubleUtil.formatRealWithSimbol(0.0);
        }
    }

    public Double getTotalCartWithDiscounts(){
        Double subTotal = this.subTotalCart;
        if (this.payment.equals(PaymentType.TICKET)){
            subTotal = subTotal - this.paymentOff;
        }
        return subTotal + this.freight.getValorDouble();
    }

    public String getTotalCartWithDiscountsFormatted(){
        return DoubleUtil.formatRealWithSimbol(this.getTotalCartWithDiscounts());
    }

}
