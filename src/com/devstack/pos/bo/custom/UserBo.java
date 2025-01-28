package com.devstack.pos.bo.custom;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo {
    public boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException;

    public boolean updateUser(UserDto userDto) throws SQLException, ClassNotFoundException;

    public boolean deleteUser(String email) throws SQLException, ClassNotFoundException;

    public UserDto findUser(String email) throws SQLException, ClassNotFoundException;

    public List<UserDto> findAllUser() throws SQLException, ClassNotFoundException;
}
