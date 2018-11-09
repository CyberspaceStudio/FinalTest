package com.dao;

import com.entity.Chair;
import com.util.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChairImpl implements Dao<Chair> {
    @Override
    public boolean insert(Chair chair) throws SQLException {
        //位置存储信息不明确，一个位置还是分为x,y
        String sql = "INSERT INTO Chair(chairId,position_x,position_y) VALUES (?,?,?)";
        //String sql = "INSERT INTO Chair(chairId,position) VALUES (?,?)";
        Object[] params = new Object[]{chair.getId(),chair.getPosition_x(),chair.getPosition_y()};
        return JdbcUtils.update(sql, params);

    }

    @Override
    public boolean delete(String key,String keyword) throws SQLException{
        String sql = "DELETE FROM Chair WHERE "+key+" ='"+keyword+"'";
        return JdbcUtils.update(sql);
    }


    /**
     *
     * @param fields :需要更新的字段，例如，Object[] fields = new Object[]{musicName,musicURL,fileType,emotion}
     * @param values :字段对应的值，例如，Object[] values = new Object[]{"你还要我这样",http://...","音乐","开心"}
     * @param key :关键字段
     * @param keyword：关键字段对应的值
     * @return
     * @throws SQLException
     */
    @Override
    public boolean update(Object[] fields, Object[] values, String key, String keyword) throws SQLException {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < fields.length; x++) {
            if (x < fields.length - 1)
                sb.append(fields[x]).append("=?,");
            else
                sb.append(fields[x]).append("=?");
        }
        String params = sb.toString();
        String sql = "UPDATE Chair SET " + params + " WHERE " + key + " = '" + keyword + "'";
        return JdbcUtils.update(sql, values);
    }


    @Override
    public Chair query(String key, String keyword) throws SQLException{
        String sql = "SELECT FROM Chair WHERE"+key+" ='"+keyword+"'";
        ResultSet rs = null;
        try {
            rs = JdbcUtils.query(sql);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        Chair chair = new Chair();
        if(rs.next()) {
            chair.setId(Integer.parseInt(rs.getString("chairId")));
            chair.setPosition_x(rs.getString("position_x"));
            chair.setPosition_x(rs.getString("position_y"));
        }
        return chair;
    }
}
