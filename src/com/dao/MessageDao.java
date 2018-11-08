package com.dao;

import com.entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao<T> {

        public boolean insert(T video) throws SQLException;
        public boolean delete(String key,String keyword) throws SQLException;
        public boolean update(Object[] fields, Object[] values, String key, String keyword) throws SQLException;
        public List<Message> query(String key, String keyword) throws SQLException;


}
