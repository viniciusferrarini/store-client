package br.com.slotshop.storeclient.model;

import br.com.slotshop.server.model.Product;
import br.com.slotshop.server.model.SubCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer amount;

    @Column
    private Double total;

    @JoinColumn(name = "productId", referencedColumnName = "id")
    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart", referencedColumnName = "id")
    @JsonBackReference
    private Cart cart;

    public Double getTotalValue(){
        return this.product.getValue() * amount;
    }

}
