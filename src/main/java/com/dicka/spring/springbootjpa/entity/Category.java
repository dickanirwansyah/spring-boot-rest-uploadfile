package com.dicka.spring.springbootjpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category",
        catalog = "spring_boot")
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory")
    private int idcategory;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "valid")
    private boolean valid;

    public int getIdcategory(){
        return idcategory;
    }

    public void setIdcategory(int idcategory){
        this.idcategory = idcategory;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean isValid(){
        return valid;
    }

    public void setValid(boolean valid){
        this.valid = valid;
    }
}
