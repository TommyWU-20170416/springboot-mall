package com.tommywu.springbootmall.dao;

import com.tommywu.springbootmall.dto.ProductRequest;
import com.tommywu.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
