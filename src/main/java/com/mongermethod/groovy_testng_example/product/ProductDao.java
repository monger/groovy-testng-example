package com.mongermethod.groovy_testng_example.product;

import java.util.List;

public interface ProductDao {
    void saveProduct(Product product);
    Product loadProduct(String sku);
    void deleteProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductSubset(String[] skus);
}
