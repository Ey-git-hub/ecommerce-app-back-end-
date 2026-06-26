package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartRequest;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("items/{productId}")
    public ResponseEntity<Void> removeFromCart(@RequestHeader("x-user-id") String userId,@PathVariable Long productId){
        boolean deleted=cartService.deleteItemFromCart(userId,productId);
        return deleted?ResponseEntity.noContent().build():ResponseEntity.notFound().build();

    }
    @GetMapping
    public ResponseEntity<List<Cart>> fetchAllCartItems(@RequestHeader("x-user-id") String userId){
    return ResponseEntity.ok(cartService.getCart(userId));

}}
