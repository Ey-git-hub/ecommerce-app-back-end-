package com.app.ecommerce.controller;

import com.app.ecommerce.dto.ProductRequest;
import com.app.ecommerce.dto.ProductResponse;
import com.app.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }
    @PutMapping("/id")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,@RequestBody ProductRequest productRequest){
        return productService.updateProduct(id,productRequest)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<ResponseEntity<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
