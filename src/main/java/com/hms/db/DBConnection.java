package com.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn;

    public static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                // 1️⃣ Try Railway / cloud DB first
                String publicUrl = System.getenv("MYSQL_PUBLIC_URL");

                if (publicUrl != null && !publicUrl.isEmpty()) {
                    // Convert mysql:// → jdbc:mysql://
                    String jdbcUrl = publicUrl.replace("mysql://", "jdbc:mysql://")
                            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

                    conn = DriverManager.getConnection(jdbcUrl);

                } else {
                    // 2️⃣ Fallback to local DB (for local development)
                    conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hospital?useSSL=false&serverTimezone=UTC",
                        "root",
                        ""
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
