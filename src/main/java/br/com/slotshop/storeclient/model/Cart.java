package br.com.slotshop.storeclient.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "Cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Double subTotalCart;

    private Double totalCart;

    private Integer totalAmount;

    @DBRef
    private List<CartProduct> cartProducts;

}
