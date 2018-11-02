package com.dao;

import com.entity.Video;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface VideoDao {
    public boolean insert(Video video) throws SQLException;
    public boolean delete(String key,String keyword) throws SQLException;
    public boolean update(Object[] fields, Object[] values, String key, String keyword) throws SQLException;
    public ResultSet query(String key, String keyword) throws SQLException;
}
