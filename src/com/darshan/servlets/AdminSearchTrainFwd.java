package com.darshan.servlets;

import java.io.IOException;

import com.darshan.constant.UserRole;
import com.darshan.utility.TrainUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/adminsearchtrainfwd")
public class AdminSearchTrainFwd extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws IOException, ServletException {

        res.setContentType("text/html");

        TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);

        RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.html");
        rd.forward(req, res);
    }
}
