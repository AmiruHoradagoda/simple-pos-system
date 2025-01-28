package com.devstack.pos.dao;

import com.devstack.pos.entity.SupperEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T extends SupperEntity, ID> extends SupperDao {
    public boolean save(T t) throws SQLException, ClassNotFoundException;

    public boolean update(T t) throws SQLException, ClassNotFoundException;

    public boolean delete(ID id) throws SQLException, ClassNotFoundException;

    public T find(ID id) throws SQLException, ClassNotFoundException;

    public List<T> findAll() throws SQLException, ClassNotFoundException;

}
