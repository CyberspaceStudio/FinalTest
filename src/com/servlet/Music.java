package com.servlet;

import com.entity.User;
import com.unit.Core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Music")
public class Music extends HttpServlet {
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


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
