package com.darshan.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.darshan.beans.TrainException;
import com.darshan.constant.UserRole;
import com.darshan.utility.TrainUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws IOException, ServletException {

        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String uName = req.getParameter("uname");
        String pWord = req.getParameter("pword");

        try {
            String message = TrainUtil.login(req, res, UserRole.ADMIN, uName, pWord);

            if ("SUCCESS".equalsIgnoreCase(message)) {

                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
                rd.include(req, res);

                pw.println("<div class='main'><p1 class='menu'>Hello, " 
                        + uName + " ! Welcome </p1></div>");

                pw.println("<div class='tab'>Hi ! Here You can Manage Train Information as per Your Requirement</div>");

            } else {

                RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
                rd.include(req, res);

                pw.println("<div class='tab'><p1 class='menu'>" 
                        + message + "</p1></div>");
            }

        } catch (Exception e) {

            throw new TrainException(
                    422,
                    this.getClass().getName() + "_FAILED",
                    e.getMessage()
            );
        }
    }
}
