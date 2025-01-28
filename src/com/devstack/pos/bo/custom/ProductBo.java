package com.devstack.pos.bo.custom;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.dto.UserDto;

import java.util.List;

public interface ProductBo {
    public boolean saveProduct(ProductDto customerDto);

    public boolean updateProduct(ProductDto customerDto);

    public boolean deleteProduct(int code);

    public CustomerDto findProduct(int code);

    public List<CustomerDto> findAllProduct();
}
