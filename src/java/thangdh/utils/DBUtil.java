/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author dhtha
 */
public class DBUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "jdbc:sqlserver://localhost:1433;databaseName=HanaShop";
            conn = DriverManager.getConnection(sql, "sa", "12345678");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
