package com.tommywu.springbootmall.controller;

import com.tommywu.springbootmall.constant.ProductCategory;
import com.tommywu.springbootmall.dto.ProductQueryParams;
import com.tommywu.springbootmall.dto.ProductRequest;
import com.tommywu.springbootmall.model.Product;
import com.tommywu.springbootmall.service.ProductService;
import com.tommywu.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查詢商品清單
     * - 查詢條件
     * - 排序
     * - 分頁
     */
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){

        // 整理 查詢條件
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> product = productService.getProduct(productQueryParams);

        Integer total = productService.countProduct(productQueryParams);
        // 依照 RESTful，這是因為查詢操作本質上是成功的（沒有發生錯誤），只是結果為空下面的
        // GET 404 表示請求的資源不存在

        // form Page
        Page<Product> page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(product);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    /**
     * 為什麼刪除商品不用再查詢檢查再不再
     * 是因為前端只要可以刪除就好，不用返回查不到(可以跟前端討論)
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
