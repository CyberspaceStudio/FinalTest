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
    public static void release( Statement stmt, Connection conn) throws SQLException{
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    //通用的增删改方法
    public static boolean update(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        boolean r = false;

        pstmt = conn.prepareStatement(sql);
        //for(Object param : params)   这样写是有错误的，会有空指针异常，要写成如下的方法：
        for (int i = 0; params != null && i < params.length; i++)
            pstmt.setObject(i + 1, params[i]);

        //返回结果
        int row = pstmt.executeUpdate();
        if (row > 0)
            r =  true;
        else {
            release(pstmt, conn);
        }
        return r;
    }

    public static boolean update(String sql) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        boolean r = false;

        pstmt = conn.prepareStatement(sql);

        int row = pstmt.executeUpdate();
        if (row > 0)
            r =  true;
        else {
            release(pstmt, conn);
        }
        return r;
    }

    //实现一个通用的查询方法
    public static ResultSet query(String sql) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        conn = getConnection();
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (rs!=null) {
            return rs;
        }else{
            release(rs, pstmt, conn);
            return null;
        }
    }
}