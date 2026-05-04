package com.darshan.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.darshan.beans.TrainException;
import com.darshan.constant.ResponseCode;
import com.darshan.constant.UserRole;
import com.darshan.service.TrainService;
import com.darshan.service.TrainServiceImpl;
import com.darshan.utility.TrainUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admincancletrain")
public class AdminCancleTrain extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TrainService trainService = new TrainServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        // Validate Admin Authorization
        TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);

        try {
            String trainNo = req.getParameter("trainno");

            String message = trainService.deleteTrainById(trainNo);

            if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(message)) {

                RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.html");
                rd.include(req, res);

                pw.println("<div class='main'><p1 class='menu'>Train number "
                        + trainNo + " has been Deleted Successfully.</p1></div>");

            } else {

                RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.html");
                rd.include(req, res);

                pw.println("<div class='tab'><p1 class='menu'>Train No."
                        + trainNo + " is Not Available !</p1></div>");
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
