package com.app.ecommerce.controller;

import com.app.ecommerce.dto.ProductRequest;
import com.app.ecommerce.dto.ProductResponse;
import com.app.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/api/product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }
}
