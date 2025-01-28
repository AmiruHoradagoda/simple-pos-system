package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.entity.User;
import com.devstack.pos.util.PasswordManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Override
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES ( ?,? )";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, PasswordManager.encryptPassword(user.getPassword()));
        return preparedStatement.executeUpdate() > 0;

    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public User findUser(String email) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}
