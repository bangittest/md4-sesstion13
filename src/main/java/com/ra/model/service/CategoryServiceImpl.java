package com.ra.model.service;

import com.ra.model.dao.CategoryDao;
import com.ra.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public boolean saveOfUpdate(Category category) {
        categoryDao.saveOfUpdate(category);
        return false;
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
    categoryDao.delete(id);
    }
}
