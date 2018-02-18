package com.dicka.spring.springbootjpa.dao;

import com.dicka.spring.springbootjpa.entity.Product;

import java.util.List;

public interface ProductDao {

    Product findIdproduct(String idproduct);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    List<Product> findAllProduct();

    void deleteProduct(Product product);
}
