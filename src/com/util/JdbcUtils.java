package com.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUtils {

    private static DataSource ds;

    static {
        ds = new ComboPooledDataSource("mysql");//配置文件中设置的内容
    }


    //获取与指定数据的连接
    public static DataSource getSource() {
        return ds;
    }


    //获得与指定数据库的连接

    public static Connection getConnection() throws SQLException {

        //从连接池返回一个连接
        return ds.getConnection();

    }

    //释放资源

    public static void release(ResultSet rs, Statement stmt, Connection conn) throws SQLException{

        if (rs != null) {
            rs.close();
            rs = null;
        }

        if (stmt != null) {
            stmt.close();
            stmt = null;
        }

        if (conn != null) {
            conn.close();
            conn = null;
        }

    }

    public static void release(Connection conn) throws SQLException{
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    //通用的增删改方法
    public static boolean update(String sql, Object[] params) throws SQLException {


        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //预编译sql

        pstmt = conn.prepareStatement(sql);
        //for(Object param : params)   这样写是有错误的，会有空指针异常，要写成如下的方法：
        for (int i = 0; params != null && i < params.length; i++)
            pstmt.setObject(i + 1, params[1]);//由于不知道是什么类型的，故可以用Object

        //返回结果
        int row = pstmt.executeUpdate();
        if (row > 0)
            return true;
        else {
            release(rs, pstmt, conn);
            return false;
        }
    }

    //实现一个通用的查询方法

    public static ResultSet query(String sql, Object[] params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = getConnection();

            //写sql

            //预编译sql

        pstmt = conn.prepareStatement(sql);

            //替换参数

        for (int i = 0; params != null && i < params.length; i++) {
            pstmt.setObject(i + 1, params[1]);//由于不知道是什么类型的，故可以用Object
        }
            //发送sql
        rs = pstmt.executeQuery();

        if (rs!=null) {
            return rs;
        }else{
            release(rs, pstmt, conn);
            return null;
        }

    }

}