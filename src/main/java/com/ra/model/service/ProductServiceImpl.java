package com.ra.model.service;

import com.ra.model.dao.ProductDao;
import com.ra.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao productDao;
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean saveOfUpdate(Product product) {
        return productDao.saveOfUpdate(product);
    }

    @Override
    public Product findById(Integer integer) {
        return productDao.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        productDao.delete(integer);
    }
}
