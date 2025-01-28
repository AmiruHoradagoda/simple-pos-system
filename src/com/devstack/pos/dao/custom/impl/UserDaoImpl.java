package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.entity.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean saveProduct(User customer) {
        return false;
    }

    @Override
    public boolean updateProduct(User customer) {
        return false;
    }

    @Override
    public boolean deleteProduct(String email) {
        return false;
    }

    @Override
    public User findProduct(String email) {
        return null;
    }

    @Override
    public List<User> findAllProduct() {
        return null;
    }
}
