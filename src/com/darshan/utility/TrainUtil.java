package com.darshan.utility;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import com.darshan.beans.TrainException;
import com.darshan.beans.UserBean;
import com.darshan.constant.ResponseCode;
import com.darshan.constant.UserRole;
import com.darshan.service.UserService;
import com.darshan.service.UserServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TrainUtil {

    // Read cookie by key
    public static Optional<String> readCookie(HttpServletRequest request, String key) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    // Login Method
    public static String login(HttpServletRequest request,
                               HttpServletResponse response,
                               UserRole userRole,
                               String username,
                               String password) {

        UserService userService = new UserServiceImpl(userRole);

        String responseCode = ResponseCode.UNAUTHORIZED.toString();

        try {
            UserBean user = userService.loginUser(username, password);

            // Store user in ServletContext
            request.getServletContext()
                   .setAttribute(userRole.toString(), user);

            // Store in session
            request.getSession().setAttribute("uName", user.getFName());
            request.getSession().setAttribute("mailid", user.getMailId());

            // Create Cookie
            Cookie cookie = new Cookie(
                    "sessionIdFor" + userRole.toString(),
                    UUID.randomUUID().toString()
            );

            cookie.setMaxAge(600);   // 10 minutes
            response.addCookie(cookie);

            responseCode = ResponseCode.SUCCESS.toString();

        } catch (TrainException e) {
            responseCode += " : " + e.getMessage();
        }

        return responseCode;
    }

    // Check Login Status
    public static boolean isLoggedIn(HttpServletRequest request,
                                     UserRole userRole) {

        Optional<String> sessionId =
                readCookie(request, "sessionIdFor" + userRole.toString());

        return sessionId != null && sessionId.isPresent();
    }

    // Validate Authorization
    public static void validateUserAuthorization(HttpServletRequest request,
                                                 UserRole userRole)
                                                 throws TrainException {

        if (!isLoggedIn(request, userRole)) {
            throw new TrainException(ResponseCode.SESSION_EXPIRED);
        }
    }

    // Logout Method
    public static boolean logout(HttpServletResponse response) {

        Cookie cookie1 = new Cookie(
                "sessionIdFor" + UserRole.ADMIN.toString(),
                UUID.randomUUID().toString()
        );
        cookie1.setMaxAge(0);

        Cookie cookie2 = new Cookie(
                "sessionIdFor" + UserRole.CUSTOMER.toString(),
                UUID.randomUUID().toString()
        );
        cookie2.setMaxAge(0);

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return true;
    }

    // Get current username
    public static String getCurrentUserName(HttpServletRequest req) {
        return (String) req.getSession().getAttribute("uName");
    }

    // Get current email
    public static String getCurrentUserEmail(HttpServletRequest req) {
        return (String) req.getSession().getAttribute("mailid");
    }

    // Get current customer object
    public static UserBean getCurrentCustomer(HttpServletRequest req) {
        return (UserBean) req.getServletContext()
                             .getAttribute(UserRole.CUSTOMER.toString());
    }
}
