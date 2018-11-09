package com.servlet;

import com.dao.Dao;
import com.dao.PhotoImpl;
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

@WebServlet(name = "Photo")
public class Photo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = DealRequest.dealIt(request);
        com.entity.Photo photo = new com.entity.Photo();
        String finalEmotion = Core.judge(user);
        //实现方法接口
        Dao photoDao = new PhotoImpl();
        try {
            photo = ((PhotoImpl) photoDao).query("emotion",finalEmotion);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emotion",photo.getEmotion());
        jsonObject.put("url",photo.getUrl());
        jsonObject.put("fileType",photo.getFileType());
        response.getWriter().write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
