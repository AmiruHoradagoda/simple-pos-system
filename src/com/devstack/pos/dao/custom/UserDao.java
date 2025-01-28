package com.devstack.pos.dao.custom;

import com.devstack.pos.entity.User;

import java.util.List;

public interface UserDao {

    public boolean saveProduct (User customer);
    public boolean updateProduct (User customer);
    public boolean deleteProduct (String email);
    public User findProduct (String email);
    public List<User> findAllProduct ();
}
