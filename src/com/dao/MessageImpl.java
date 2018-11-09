package com.dao;

import com.entity.Message;
import com.util.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageImpl implements MessageDao<Message> {

    @Override
    public boolean insert(Message message) throws SQLException {
        String sql = "INSERT INTO Message(chairId,content,fileType) VALUES (?,?,?)";
        Object[] params = new Object[]{message.getId(),message.getContent(),message.getEmotion()};
        return JdbcUtils.update(sql, params);

    }

    @Override
    public boolean delete(String key,String keyword) throws SQLException{
        String sql = "DELETE FROM Message WHERE "+key+" ='"+keyword+"'";
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
        String sql = "UPDATE Message SET " + params + " WHERE " + key + " = '" + keyword + "'";
        return JdbcUtils.update(sql, values);
    }


    @Override
    public List<Message> query(String key, String keyword) throws SQLException{
        List<Message> list = new ArrayList<Message>();
        ResultSet res= JdbcUtils.query("SELECT * FROM message WHERE "+key+"='"+keyword+"'");
        while(res.next()){
            Message temp = new Message();
            temp.setId(Integer.parseInt(res.getString("chairId")));
            temp.setContent(res.getString("content"));
            temp.setEmotion(res.getString("emotion"));
            list.add(temp);
        }
        return list;
    }
}
