package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartRequest;
import com.app.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestHeader("x-user-id") String Id, @RequestBody CartRequest request){
        cartService.addCart(Id,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
