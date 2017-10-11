package br.com.slotshop.storeclient.model;

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

    private Double subTotalCart;

    private Double totalCart;

    private Integer totalAmount;

    private List<CartProduct> cartProducts;

}
