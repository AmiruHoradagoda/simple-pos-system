package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.CrudUtil;
import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.entity.User;
import com.devstack.pos.util.PasswordManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES ( ?,? )";
        return CrudUtil.execute(sql, user.getEmail(), PasswordManager.encryptPassword(user.getPassword()));
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password=? WHERE email=?";
        return CrudUtil.execute(sql, PasswordManager.encryptPassword(user.getPassword()), user.getEmail());
    }

    @Override
    public boolean delete(String email) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM user WHERE email=?";
        return CrudUtil.execute(sql, email);
    }

    @Override
    public User find(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE email=?";
        ResultSet resultSet = CrudUtil.execute(sql, email);
        if (resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            userList.add(
                    new User(
                            resultSet.getString(1),
                            resultSet.getString(2)
                    )
            );
        }
        return userList;
    }
}
