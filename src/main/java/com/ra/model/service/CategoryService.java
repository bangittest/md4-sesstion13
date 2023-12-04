package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category>findAll();

    boolean saveOfUpdate(Category category);

    Category findById(Integer id);
    void delete(Integer integer);
}
