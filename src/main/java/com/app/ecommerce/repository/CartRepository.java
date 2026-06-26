package com.app.ecommerce.repository;

import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

    Cart findByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);
}
