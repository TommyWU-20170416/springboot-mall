package com.tommywu.springbootmall.dao;

import com.tommywu.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
