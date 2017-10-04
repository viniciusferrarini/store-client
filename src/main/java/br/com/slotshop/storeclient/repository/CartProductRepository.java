package br.com.slotshop.storeclient.repository;

import br.com.slotshop.storeclient.model.CartProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartProductRepository extends MongoRepository<CartProduct, String> {
}
