package com.servlet;

import com.dao.MessageDao;
import com.dao.MessageImpl;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Message")
public class Message extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<com.entity.Message> messages = null;
        String id = request.getParameter("id");
        MessageDao md = new MessageImpl();
        try {
            messages = ((MessageImpl)md).query("id",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (com.entity.Message message: messages){
            JSONObject json = new JSONObject();
            json.put("id",message.getId());
            json.put("emotion",message.getEmotion());
            json.put("content",message.getContent());
            response.getWriter().write(json.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
