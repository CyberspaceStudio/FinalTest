package com.dao;

import com.entity.Video;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao<T> {
    public boolean insert(T filename) throws SQLException;
    public boolean delete(String key,String keyword) throws SQLException;
    public boolean update(Object[] fields, Object[] values, String key, String keyword) throws SQLException;
    public T query(String key, String keyword) throws SQLException;

}
