package com.devstack.pos.bo.custom.impl;

import com.devstack.pos.bo.custom.CustomerBo;
import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.CustomerDao;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.save(
                new Customer(
                        customerDto.getEmail(),
                        customerDto.getName(),
                        customerDto.getContact(),
                        customerDto.getSalary()
                )
        );
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.update(
                new Customer(
                        customerDto.getEmail(),
                        customerDto.getName(),
                        customerDto.getContact(),
                        customerDto.getSalary()
                )
        );
    }

    @Override
    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {
        return customerDao.delete(email);
    }

    @Override
    public CustomerDto findCustomer(String email) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.find(email);
        return new CustomerDto(
                customer.getEmail(),
                customer.getName(),
                customer.getContact(),
                customer.getSalary()
        );
    }

    @Override
    public List<CustomerDto> findAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDao.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(
                    new CustomerDto(
                            customer.getEmail(),
                            customer.getName(),
                            customer.getContact(),
                            customer.getSalary()
                    )
            );
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
}
