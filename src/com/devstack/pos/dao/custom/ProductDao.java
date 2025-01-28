package com.devstack.pos.dao.custom;

import com.devstack.pos.entity.Product;

import java.util.List;

public interface ProductDao {
    public boolean saveProduct (Product customer);
    public boolean updateProduct (Product customer);
    public boolean deleteProduct (int code); //fix error
    public Product findProduct (int code);//fix error
    public List<Product> findAllProduct ();
}
