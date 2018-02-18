package com.dicka.spring.springbootjpa.repository;

import com.dicka.spring.springbootjpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>{
}
