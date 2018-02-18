package com.dicka.spring.springbootjpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product",
        catalog = "spring_boot")
public class Product implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "idproduct", nullable = false, unique = true)
    private String idproduct;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private int price;

    /**
    @Transient
    private MultipartFile file;
    **/

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getIdproduct(){
        return idproduct;
    }

    public void setIdproduct(String idproduct){
        this.idproduct = idproduct;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

}
