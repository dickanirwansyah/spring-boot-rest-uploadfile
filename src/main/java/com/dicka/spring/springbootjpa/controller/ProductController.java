package com.dicka.spring.springbootjpa.controller;

import com.dicka.spring.springbootjpa.dao.ProductDao;
import com.dicka.spring.springbootjpa.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductDao productDao;

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    //just upload to server
    @PostMapping(value = "/api/upload")
    public @ResponseBody void upload(@RequestParam(value = "file")MultipartFile file){

        String originalName = "";
        try{
            log.debug("Upload Image "+file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            originalName = file.getOriginalFilename();

            BufferedOutputStream stream =
                    new BufferedOutputStream
                            (new FileOutputStream(new File("//var//www//html//server-poto//"+originalName)));
            stream.write(bytes);
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
            log.debug("Upload Image "+originalName+" failed !");
        }
    }


    //handling save and upload to server
    @PostMapping(value = "/api/saveupload")
    public @ResponseBody void saveupload(@RequestParam(value = "file")MultipartFile file,
                                         Product product){
        saved(product);
        String originalName = "";
        try{

            byte[] bytes = file.getBytes();
            originalName = product.getImage()+".jpg";

            BufferedOutputStream stream =
                    new BufferedOutputStream
                            (new FileOutputStream(new File("//var//www//html//server-poto//"+originalName)));
            stream.write(bytes);
            stream.close();
            System.out.println("Upload Successfully "+originalName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //handling update upload
    @PostMapping(value = "/api/updateupload")
    public @ResponseBody void updateProduct(@RequestParam(value = "file")MultipartFile file,
                                            Product product){

        updated(product);
        String originalName = "";
        try{

            byte[] bytes = file.getBytes();
            originalName = product.getImage()+".jpg";

            BufferedOutputStream stream =
                    new BufferedOutputStream
                            (new FileOutputStream(new File("//var//www//html//server-poto//"+originalName)));
            stream.write(bytes);
            stream.close();
            System.out.println("Update poto Successfully "+originalName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/api/justupdate")
    public ResponseEntity<Product> updateJustProduct(@RequestBody Product product){
        return Optional.ofNullable(productDao.updateProduct(product))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<Product>(HttpStatus.BAD_REQUEST));
    }

    private void saved(Product product){
        productDao.saveProduct(product);
    }

    private void updated(Product product){
        productDao.updateProduct(product);
    }

    //list product
    @GetMapping(value = "/api/list")
    public ResponseEntity<List<Product>> getAllProduct(){
        return Optional.ofNullable(productDao.findAllProduct())
                .map(resultset -> new ResponseEntity<>(resultset,HttpStatus.OK))
                .orElse(new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST));
    }

    //update product
    @PostMapping(value = "/api/update")
    public ResponseEntity<Product> update(@RequestBody Product product){
        return Optional.ofNullable(productDao.updateProduct(product))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.OK))
                .orElse(new ResponseEntity<Product>(HttpStatus.BAD_REQUEST));
    }

    //post create product
    @PostMapping(value = "/api/create")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return Optional.ofNullable(productDao.saveProduct(product))
                .map(resultset -> new ResponseEntity<>(resultset, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Product>(HttpStatus.BAD_REQUEST));
    }

    //delete product
    @DeleteMapping(value = "/api/delete")
    public ResponseEntity<Void> delete(@RequestParam(value = "idproduct")String idproduct){
        Product product = productDao.findIdproduct(idproduct);
        if(product == null){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        productDao.deleteProduct(product);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
