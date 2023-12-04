package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.utils.ConnectionDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDaoImpl implements ProductDao{
    @Autowired
    CategoryService categoryService;
    @Override
    public List<Product> findAll() {
        Connection connection= ConnectionDatabase.openConnection();
        CallableStatement callableStatement= null;
        List<Product> productList=new ArrayList<>();
        try {
            callableStatement = connection.prepareCall("{CALL PROC_FIND_ALL_PRODUCT}");
            ResultSet result=callableStatement.executeQuery();
            while (result.next()){
                Product product=new Product();
                product.setProductId(result.getInt("id"));
                product.setProductName(result.getString("name"));
                product.setPrice(result.getDouble("price"));
                Category category=categoryService.findById(result.getInt("category_id"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public boolean saveOfUpdate(Product product) {
        Connection connection=ConnectionDatabase.openConnection();
        int check=0;
            try {
                if (product.getProductId()==0){
                CallableStatement callableStatement=connection.prepareCall("{CALL PROC_CREATE_PRODUCT(?,?,?)}");
                    callableStatement.setString(1,product.getProductName());
                    callableStatement.setDouble(2,product.getPrice());
                    callableStatement.setInt(3,product.getCategory().getCategoryId());
                    check=callableStatement.executeUpdate();
                }else {
                     CallableStatement callableStatement= connection.prepareCall("{CALL PROC_UPDATE_PRODUCT(?,?,?,?)}");
                     callableStatement.setInt(1,product.getProductId());
                     callableStatement.setString(2,product.getProductName());
                     callableStatement.setDouble(3,product.getPrice());
                     callableStatement.setInt(4,product.getCategory().getCategoryId());
                     check=callableStatement.executeUpdate();
                }
                if (check>0){
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                ConnectionDatabase.closeConnection(connection);
            }
        return false;
    }

    @Override
    public Product findById(Integer id) {
        Product product=new Product();
        Connection connection=ConnectionDatabase.openConnection();
        try {
            CallableStatement callableStatement=connection.prepareCall("{CALL PROC_FIND_BY_ID_PRODUCT(?)}");
            callableStatement.setInt(1,id);
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                product.setProductId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                Category category=categoryService.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
        return product;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection=null;
        connection=ConnectionDatabase.openConnection();
        try {
            CallableStatement callableStatement=connection.prepareCall("{CALL PROC_DELETE_PRODUCT(?)}");
            callableStatement.setInt(1,integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
    }
}
