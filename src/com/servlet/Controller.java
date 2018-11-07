package com.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seeds = new Random().nextInt(5);
        RequestDispatcher rd = null;
        response.getWriter().println(seeds);
/*
        try {
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
*/
        seeds = 1;
        switch (seeds){
            case 1:
                rd = getServletContext().getRequestDispatcher("/video");
                rd.forward(request,response);
                break;
            case 2:
                rd = getServletContext().getRequestDispatcher("/music");
                rd.forward(request,response);
                break;
            case 3:
                rd = getServletContext().getRequestDispatcher("/photo");
                rd.forward(request,response);
                break;
            case 4:
                rd = getServletContext().getRequestDispatcher("/message");
                rd.forward(request,response);
                break;
            case 5:
                rd = getServletContext().getRequestDispatcher("/music");
                rd.forward(request,response);
                break;
            default:
                rd = getServletContext().getRequestDispatcher("/message");
                rd.forward(request,response);
                break;
        }
        response.getWriter().write("error");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
