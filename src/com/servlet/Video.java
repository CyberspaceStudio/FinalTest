package com.servlet;

import com.dao.Dao;
import com.dao.VideoImpl;
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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Video")
public class Video extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = DealRequest.dealIt(request);
        com.entity.Video video = new com.entity.Video();
        String finalEmotion = Core.judge(user);
        //实现方法接口
        Dao videoDao = new VideoImpl();
        try {
            video = ((VideoImpl) videoDao).query("emotion",finalEmotion);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emotion",video.getEmotion());
        jsonObject.put("url",video.getUrl());
        jsonObject.put("fileType",video.getFileType());
        response.getWriter().write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
