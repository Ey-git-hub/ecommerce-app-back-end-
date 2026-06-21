package com.app.ecommerce.service;

import com.app.ecommerce.dto.ProductRequest;
import com.app.ecommerce.dto.ProductResponse;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product=new Product();
        updateProductFromRequest(product,productRequest);
        Product savedProduct=productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    private ProductResponse mapToProductResponse(Product savedProduct) {

    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
    }
}
