package br.com.slotshop.storeclient.model;

import br.com.slotshop.server.model.Product;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer amount;

    private Double total;

    private Product product;

    private Cart cart;

    public Double getTotalValue(){
        return this.product.getValue() * amount;
    }

}
