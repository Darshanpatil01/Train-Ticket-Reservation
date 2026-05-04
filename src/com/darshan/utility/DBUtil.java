package com.darshan.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.darshan.beans.TrainException;
import com.darshan.constant.ResponseCode;

public class DBUtil {

    private static Connection con;

    static {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("application");

            Class.forName(rb.getString("driverName"));

            System.out.println("Connecting to MySQL...");
            System.out.println("URL: " + rb.getString("connectionString"));
            System.out.println("User: " + rb.getString("username"));

            con = DriverManager.getConnection(
                    rb.getString("connectionString"),
                    rb.getString("username"),
                    rb.getString("password")
            );

            System.out.println("✅ MySQL Connection Success!!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver Not Found");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws TrainException {

        try {
            if (con == null || con.isClosed()) {
                throw new TrainException(ResponseCode.DATABASE_CONNECTION_FAILURE);
            }

        } catch (SQLException e) {
            throw new TrainException(ResponseCode.DATABASE_CONNECTION_FAILURE);
        }

        return con;
    }
}
