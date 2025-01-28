package com.devstack.pos.bo.custom;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.UserDto;

import java.util.List;

public interface UserBo {
    public boolean saveUser(UserDto customerDto);

    public boolean updateUser(UserDto customerDto);

    public boolean deleteUser(String email);

    public CustomerDto findUser(String email);

    public List<CustomerDto> findAllUser();
}
