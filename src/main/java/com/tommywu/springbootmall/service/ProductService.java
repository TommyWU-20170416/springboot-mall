package com.tommywu.springbootmall.service;

import com.tommywu.springbootmall.constant.ProductCategory;
import com.tommywu.springbootmall.dto.ProductQueryParams;
import com.tommywu.springbootmall.dto.ProductRequest;
import com.tommywu.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


    Integer countProduct(ProductQueryParams productQueryParams);
}
