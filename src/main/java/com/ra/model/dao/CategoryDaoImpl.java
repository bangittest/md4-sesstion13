package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.utils.ConnectionDatabase;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{
    @Override
    public List<Category> findAll() {
        Connection connection=ConnectionDatabase.openConnection();
        List<Category>categoryList=new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PROC_FIND_ALL_CATEGORY");
            ResultSet rs =callableStatement.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category.setCategoryId(rs.getInt("id"));
                category.setCategoryName(rs.getString("name"));
                category.setCategoryStatus(rs.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public boolean saveOfUpdate(Category category) {
        Connection connection=ConnectionDatabase.openConnection();
        int check=0;
        try {
           if (category.getCategoryId()==0){
               CallableStatement  callableStatement = connection.prepareCall("CALL PROC_CREATE_CATEGORY(?,?)");
               callableStatement.setString(1,category.getCategoryName());
               callableStatement.setBoolean(2,category.isCategoryStatus());
               check= callableStatement.executeUpdate();
           }else {
               CallableStatement  callableStatement = connection.prepareCall("CALL PROC_UPDATE_CATEGORY(?,?,?)");
               callableStatement.setInt(1,category.getCategoryId());
               callableStatement.setString(2,category.getCategoryName());
               callableStatement.setBoolean(3,category.isCategoryStatus());
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
    public Category findById(Integer id) {
        Connection connection=null;
        connection=ConnectionDatabase.openConnection();
        Category category=new Category();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PROC_FIND_BY_ID_CATEGORY(?) ");
            callableStatement.setInt(1,id);
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()) {
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setCategoryStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
        return category;
    }

    @Override
    public void delete(Integer id) {
        Connection connection=ConnectionDatabase.openConnection();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement("CALL PROC_DELETE_CATEGORY(?)");
            preparedStatement.setInt(1, id);
            int check=preparedStatement.executeUpdate();
            if (check>0){
                System.out.println(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
    }
}
