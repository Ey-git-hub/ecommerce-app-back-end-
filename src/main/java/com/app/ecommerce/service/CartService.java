package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartRequest;
import com.app.ecommerce.repository.ProductRepository;
import com.app.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserRepository userRepository;
    private final ProductRepository product
    public void addCart(String id, CartRequest request) {


    }
}
