package com.devstack.pos.bo.custom.impl;

import com.devstack.pos.bo.custom.ProductBo;
import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.ProductDao;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.entity.Product;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.List;

public class ProductBoImpl implements ProductBo {
    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    @Override
    public boolean saveProduct(ProductDto productDto) throws SQLException, ClassNotFoundException {
        return productDao.save(
                new Product(productDto.getCode(), productDto.getDescription())
        );
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        return false;
    }

    @Override
    public boolean deleteProduct(int code) {
        return false;
    }

    @Override
    public CustomerDto findProduct(int code) {
        return null;
    }

    @Override
    public List<CustomerDto> findAllProduct() {
        return null;
    }


    public int getLastProductId() throws SQLException, ClassNotFoundException {
        return productDao.getLastProductId();
    }
}
