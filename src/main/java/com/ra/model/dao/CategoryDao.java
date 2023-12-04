package com.ra.model.dao;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category>findAll();

    boolean saveOfUpdate(Category category);

    Category findById(Integer id);
    void delete(Integer id);
}
