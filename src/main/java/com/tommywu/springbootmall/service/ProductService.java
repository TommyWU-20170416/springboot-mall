package com.tommywu.springbootmall.service;

import com.tommywu.springbootmall.dto.ProductRequest;
import com.tommywu.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
