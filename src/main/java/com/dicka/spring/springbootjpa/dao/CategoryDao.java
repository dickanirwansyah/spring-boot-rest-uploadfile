package com.dicka.spring.springbootjpa.dao;

import com.dicka.spring.springbootjpa.entity.Category;

import java.util.List;

public interface CategoryDao {

    Category insertCategory(Category category);

    Category updateCategory(Category category);

    Category findIdcategory(int idcategory);

    List<Category> findAll();

    Category disabledCategory(Category category);
}
