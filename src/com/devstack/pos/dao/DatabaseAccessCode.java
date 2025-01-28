package com.devstack.pos.dao;

import com.devstack.pos.dao.custom.CustomerDao;
import com.devstack.pos.dao.custom.ProductDao;
import com.devstack.pos.dao.custom.UserDao;
import com.devstack.pos.dao.custom.impl.CustomerDaoImpl;
import com.devstack.pos.dao.custom.impl.ProductDaoImpl;
import com.devstack.pos.dao.custom.impl.UserDaoImpl;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.entity.Product;
import com.devstack.pos.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//====== <User Management>=======

public class DatabaseAccessCode {

    CustomerDao customerDao = new CustomerDaoImpl();
    UserDao userDao = new UserDaoImpl();
    ProductDao productDao = new ProductDaoImpl();


    public boolean createUser(
            String email, String password) throws ClassNotFoundException, SQLException {

        return userDao.save(new User(email, password));
    }

    public UserDto findUser(String email) throws ClassNotFoundException, SQLException {
        User user = userDao.find(email);
        if (user != null) {
            return new UserDto(
                    user.getEmail(),
                    user.getPassword()
            );
        }
        return null;
    }
//====== </User Management>=======

    //====== <Customer Management> =======
    public boolean createCustomer(
            String email,
            String name,
            String contact,
            double salary
    ) throws ClassNotFoundException, SQLException {

        return customerDao.save(new Customer(email, name, contact, salary));
    }

    public boolean updateCustomer(
            String email,
            String name,
            String contact,
            double salary
    ) throws ClassNotFoundException, SQLException {

        return customerDao.update(new Customer(email, name, contact, salary));
    }

    public CustomerDto findCustomer(
            String email
    ) throws ClassNotFoundException, SQLException {
        Customer customer = customerDao.find(email);
        if (customer != null) {
            return new CustomerDto(
                    customer.getEmail(), customer.getName(), customer.getContact(), customer.getSalary()
            );
        }
        return null;
    }

    public boolean deleteCustomer(String email) throws ClassNotFoundException, SQLException {

        return customerDao.delete(email);
    }


    public List<CustomerDto> findAllCustomers() throws ClassNotFoundException, SQLException {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerDao.findAll()) {
            customerDtos.add(new CustomerDto(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            ));
        }


        return customerDtos;
    }

    public List<CustomerDto> searchCustomers(String searchText) throws ClassNotFoundException, SQLException {
        searchText = "%" + searchText + "%";
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerDao.searchCustomers(searchText)) {
            customerDtos.add(new CustomerDto(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            ));
        }

        return customerDtos;
    }


//====== </Customer Management> =======

//===== <Product Management> ==========

    public int getLastProductId() throws SQLException, ClassNotFoundException {

        return productDao.getLastProductId();
    }

    public boolean saveProduct(int code, String description) throws SQLException, ClassNotFoundException {

        return productDao.save(
                new Product(code, description)
        );
    }
}