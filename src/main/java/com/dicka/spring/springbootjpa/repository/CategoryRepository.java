package com.dicka.spring.springbootjpa.repository;

import com.dicka.spring.springbootjpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
