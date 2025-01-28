package com.devstack.pos.dao.custom;

import com.devstack.pos.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    public boolean saveProduct(Product product) throws SQLException, ClassNotFoundException;

    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException;

    public boolean deleteProduct(int code) throws SQLException, ClassNotFoundException; //fix error

    public Product findProduct(int code) throws SQLException, ClassNotFoundException;//fix error

    public List<Product> findAllProduct() throws SQLException, ClassNotFoundException;
}
