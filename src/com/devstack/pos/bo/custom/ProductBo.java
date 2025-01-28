package com.devstack.pos.bo.custom;

import com.devstack.pos.bo.SupperBo;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductBo extends SupperBo {
    public boolean saveProduct(ProductDto productDto) throws SQLException, ClassNotFoundException;

    public boolean updateProduct(ProductDto productDto);

    public boolean deleteProduct(int code);

    public CustomerDto findProduct(int code);

    public List<CustomerDto> findAllProduct();

    public int getLastProductId() throws SQLException, ClassNotFoundException;
}
