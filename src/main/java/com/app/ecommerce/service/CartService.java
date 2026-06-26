package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartRequest;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repository.CartRepository;
import com.app.ecommerce.repository.ProductRepository;
import com.app.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    public Boolean addCart(String userId, CartRequest request) {
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isEmpty()){
            return false;
        }
        Product product =productOpt.get();
        if(product.getStockQuantity() < request.getQuantity()) {
            return false;
        }
        Optional<User> userOpt=userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()){
            return false;
        }
        User user=userOpt.get();
        Cart existingCart=cartRepository.findByUserAndProduct(user,product);
        if(existingCart!=null){
           //update the quantity
            existingCart.setQuantity(existingCart.getQuantity()+request.getQuantity());
            existingCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCart.getQuantity())));
            cartRepository.save(existingCart);
        }else{
            //create new cart item
            Cart cart=new Cart();
            cart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cart.setQuantity(request.getQuantity());
            cart.setProduct(product);
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return true;
    }
}
