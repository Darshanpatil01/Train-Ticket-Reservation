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

@WebServlet("/cancletrainfwd")
public class AdminCancleTrainFwd extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");

        // Validate Admin Authorization
        TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);

        RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.html");
        rd.forward(req, res);
    }
}
