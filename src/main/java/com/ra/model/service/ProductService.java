package com.ra.model.service;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product>findAll();
    boolean saveOfUpdate(Product product);
    Product findById(Integer integer);
    void delete(Integer integer);

}
