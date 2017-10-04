package br.com.slotshop.storeclient.model;

import br.com.slotshop.server.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "CartProduct")
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Integer amount;

    private Double total;

    @JsonBackReference
    private Product product;

    public Double getTotalValue(){
        return this.product.getValue() * amount;
    }

}
