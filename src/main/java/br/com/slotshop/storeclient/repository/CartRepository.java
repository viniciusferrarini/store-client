package br.com.slotshop.storeclient.repository;

import br.com.slotshop.storeclient.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByToken(String token);

}
