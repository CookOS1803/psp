package com.cookos.util;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

public class ConnectorDB {

    private static final Logger logger = LogManager.getLogger(ConnectorDB.class);

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

        logger.info("connection established");

        return DriverManager.getConnection(url, user, pass);
    }

}