package com.tommywu.springbootmall.service;

import com.tommywu.springbootmall.constant.ProductCategory;
import com.tommywu.springbootmall.dto.ProductRequest;
import com.tommywu.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProduct(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
