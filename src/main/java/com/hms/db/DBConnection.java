package com.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital?useSSL=false&serverTimezone=UTC",
                    "root",
                    ""  // Empty password
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
