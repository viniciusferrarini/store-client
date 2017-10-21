package br.com.slotshop.storeclient.model.dto;

import br.com.slotshop.server.enumeration.PaymentType;
import br.com.slotshop.server.model.UserAdress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {

    private PaymentType paymentType;

    private String cardHolder;

    private String cardNumber;

    private Integer expirationMonth;

    private Integer expirationYear;

    private Integer verificationNumber;

    private Integer amountParcel;

    private UserAdress userAdress;

}

