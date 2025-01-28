package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.custom.CustomerDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES ( ?,?,?,? )";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getEmail());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getContact());
        preparedStatement.setDouble(4, customer.getSalary());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE  customer SET name=?,contact=?, salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getEmail());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getContact());
        preparedStatement.setDouble(4, customer.getSalary());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteCustomer(String email)throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Customer findCustomer(String email) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Customer> findAllCustomer() throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }

        return customers;
    }

    @Override
    public List<Customer> searchCustomers(String searchText) throws ClassNotFoundException, SQLException {
        return null;
    }
}
