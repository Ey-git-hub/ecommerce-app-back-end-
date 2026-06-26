package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartRequest;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repository.CartRepository;
import com.app.ecommerce.repository.ProductRepository;
import com.app.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public boolean deleteItemFromCart(String userId, Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> userOpt=userRepository.findById(Long.valueOf(userId));

        if(productOpt.isPresent() && userOpt.isPresent()){
            cartRepository.deleteByUserAndProduct(userOpt.get(),productOpt.get());
        }
        return false;
    }

//    public Boolean fetchItemsFromCart(String userId) {
////        Optional<Product> productOpt = productRepository.findById(productId);
//        Optional<User> userOpt=userRepository.findById(Long.valueOf(userId));
//        if(userOpt.isPresent()){
//            cartRepository.findByUser(userOpt.get());
//        }
//    return false;
//    }

    public List<Cart> getCart(String userId) {
        return  userRepository.findById(Long.valueOf(userId))
                .map(cartRepository::findByUser)
                .orElseGet(List::of);

    }
}
