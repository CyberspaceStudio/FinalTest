package com.servlet;

import com.entity.User;
import com.unit.Core;
import org.json.JSONObject;

import java.io.IOException;

public class Judge extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
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
        String emotion = Core.judge(user);
        response.getWriter().write(emotion);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
