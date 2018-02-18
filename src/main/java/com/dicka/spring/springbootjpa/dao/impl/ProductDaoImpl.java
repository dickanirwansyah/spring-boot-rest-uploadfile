package com.dicka.spring.springbootjpa.dao.impl;

import com.dicka.spring.springbootjpa.dao.ProductDao;
import com.dicka.spring.springbootjpa.entity.Product;
import com.dicka.spring.springbootjpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
@Service
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product findIdproduct(String idproduct) {
        return productRepository.findOne(idproduct);
    }

    @Override
    public Product saveProduct(Product product) {
       product.setImage("POTO-"+UUID.randomUUID().toString().substring(26));
       return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(!entityManager.contains(product))
            product = entityManager.merge(product);
        return product;
    }


    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
