package com.dicka.spring.springbootjpa.controller;


import com.dicka.spring.springbootjpa.dao.CategoryDao;
import com.dicka.spring.springbootjpa.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    //list category
    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getAll(){
        return Optional.ofNullable(categoryDao.findAll())
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<List<Category>>(HttpStatus.BAD_REQUEST));
    }

    //category created
    @PostMapping(value = "/category/create")
    public ResponseEntity<Category> getAdd(@RequestBody Category category){
        return Optional.ofNullable(categoryDao.insertCategory(category))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }

    //category disabled
    @PostMapping(value = "/category/disabled")
    public ResponseEntity<Category> disabled(@RequestBody Category category){
        return Optional.ofNullable(categoryDao.disabledCategory(category))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }

    //category find byId
    @GetMapping(value = "/category/{idcategory}")
    public ResponseEntity<Category> getId(@PathVariable("idcategory")String idcategory){
        return Optional.ofNullable(categoryDao.findIdcategory(Integer.parseInt(idcategory)))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }

    //category find get by request param
    @GetMapping(value = "/category/get")
    public ResponseEntity<Category> getIdget(@RequestParam("idcategory")String idcategory){
        return Optional.ofNullable(categoryDao.findIdcategory(Integer.parseInt(idcategory)))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }

    //category updated
    @PostMapping(value = "/category/update")
    public ResponseEntity<Category> update(@RequestBody Category category){
        return Optional.ofNullable(categoryDao.updateCategory(category))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }
}
