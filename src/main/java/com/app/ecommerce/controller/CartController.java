package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartRequest;
import com.app.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping
    public ResponseEntity<String> addCart(@RequestHeader("x-user-id") String userId, @RequestBody CartRequest request){
        if(!cartService.addCart(userId,request)){
            return ResponseEntity.badRequest().body("product out of stock or product or user not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
