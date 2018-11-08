package com.util;

import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DealRequest{
    public static User dealIt(HttpServletRequest request) throws ServletException, IOException {
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
        return user;
    }
}
