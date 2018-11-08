package com.servlet;

import com.dao.Dao;
import com.dao.VideoImpl;
import com.entity.User;
import com.unit.Core;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Video")
public class Video extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String weather = request.getParameter("weather");
        float positionX = 0;
        float positionY = 0;
        try {
            positionX = Float.parseFloat(request.getParameter("positionX"));
            positionY = Float.parseFloat(request.getParameter("positionY"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        User user = new User();
        user.setPositionX(positionX);
        user.setPositionY(positionY);
        user.setWeather(weather);
        String finalEmtion = Core.judge(user);
        //实现方法接口
        Dao videoDao = new VideoImpl();
        ResultSet rs = null;
        JSONObject jsonObject = new JSONObject();
        try{
            rs = Dao.query("emotion",finalEmtion);
            jsonObject.put("emotion",finalEmtion);
            jsonObject.put("url",rs.getString("url"));
            //多此一举的方法我快疯了
            com.entity.Video video = new com.entity.Video();
            video.setUrl(rs.getString("url"));
            video.decodeFileType();
            jsonObject.put("fileType",video.getFileType());
            //以上全是多此一举
            response.getWriter().write(jsonObject.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
