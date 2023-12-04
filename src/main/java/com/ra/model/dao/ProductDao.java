package com.ra.model.dao;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product>findAll();
    boolean saveOfUpdate(Product product);

    Product findById(Integer integer);
    void delete(Integer integer);
}
