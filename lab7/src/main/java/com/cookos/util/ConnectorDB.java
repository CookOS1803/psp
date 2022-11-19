package com.cookos.util;

import java.sql.*;
import java.util.*;

public class ConnectorDB {

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        var resource = ResourceBundle.getBundle("db", Locale.getDefault());
        var url = resource.getString("db.url");
        var user = resource.getString("db.user");
        var pass = resource.getString("db.password");

        return DriverManager.getConnection(url, user, pass);
    }

}