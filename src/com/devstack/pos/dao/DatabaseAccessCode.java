package com.devstack.pos.dao;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.util.PasswordManager;

import java.sql.*;
import java.util.List;

//====== <User Management>=======

public class DatabaseAccessCode {
    public static boolean createUser(
            String email, String password
    ) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotikka",
                "root", "1234");
        String sql = "INSERT INTO user VALUES ( ?,? )";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, PasswordManager.encryptPassword(password));
        return preparedStatement.executeUpdate() > 0;
    }

    public static UserDto findUser(String email) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotikka",
                "root", "1234");
        String sql = "SELECT * FROM user WHERE email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new UserDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }
//====== </User Management>=======

    //====== Customer Management =======
    public static boolean createCustomer(
            String email,
            String name,
            String contact,
            double salary
    ) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotikka",
                "root", "1234");
        String sql = "INSERT INTO customer VALUES ( ?,?,?,? )";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, contact);
        preparedStatement.setDouble(4, salary);
        return preparedStatement.executeUpdate() > 0;
    }

    public static boolean updateCustomer(
            String email,
            String name,
            String contact,
            double salary
    ) {
        return false;
    }

    public static CustomerDto findCustomer(
            String email
    ) {
        return null;
    }

    public static boolean deleteCustomer(
            String email
    ) {
        return false;
    }


    public static List<CustomerDto> findAllCustomers(

    ) {
        return null;
    }

    public static CustomerDto searchCustomers(
            String searchText
    ) {
        return null;
    }


}
