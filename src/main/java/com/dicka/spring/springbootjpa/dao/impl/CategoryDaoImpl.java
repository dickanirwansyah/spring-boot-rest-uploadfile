package com.dicka.spring.springbootjpa.dao.impl;

import com.dicka.spring.springbootjpa.dao.CategoryDao;
import com.dicka.spring.springbootjpa.entity.Category;
import com.dicka.spring.springbootjpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
@Service
public class CategoryDaoImpl implements CategoryDao{

    private final CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CategoryDaoImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category insertCategory(Category category) {
        category.setValid(true);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        if(category.getIdcategory() != 0){
            return categoryRepository.save(category);
        }
        return category;
    }

    @Override
    public Category findIdcategory(int idcategory) {
        return categoryRepository.findOne(idcategory);
    }

    @Override
    public List<Category> findAll() {
        String jpql = "SELECT c FROM Category c WHERE c.valid=true";
        return entityManager.createQuery(jpql)
                .getResultList();
    }

    @Override
    public Category disabledCategory(Category category){
        if(category.getIdcategory() != 0){
            category.setValid(false);
            return categoryRepository.save(category);
        }
        return category;
    }
}
