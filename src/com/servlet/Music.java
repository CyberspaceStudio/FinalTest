package com.servlet;

import com.dao.Dao;
import com.dao.MusicDaoImpl;
import com.entity.User;
import com.util.Core;
import com.util.DealRequest;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Music")
public class Music extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = DealRequest.dealIt(request);
        com.entity.Music music= new com.entity.Music();
        String finalEmotion = Core.judge(user);
        //实现方法接口
        Dao musicDao = new MusicDaoImpl();
        try {
            music = ((MusicDaoImpl)musicDao).query("emotion",finalEmotion);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emotion",music.getEmotion());
        jsonObject.put("url",music.getUrl());
        jsonObject.put("fileType",music.getFileType());
        response.getWriter().write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
