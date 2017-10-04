package br.com.slotshop.storeclient.repository;

import br.com.slotshop.storeclient.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

}
