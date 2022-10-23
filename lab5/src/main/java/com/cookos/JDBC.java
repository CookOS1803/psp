package com.cookos;

import java.sql.*;

public class JDBC implements AutoCloseable {

    private Connection connection = null;
    public Connection getConnection() {
        return connection;
    }

    private String username;
    private String password;
    private String database;

    public JDBC(String username, String password, String database) throws SQLException {
        this.username = username;
        this.password = password;
        this.database = database;

        connect();
    }

    public JDBC(String username, String password) throws SQLException {
        this(username, password, "");
    }

    public void connect() throws SQLException {

        if (connection != null)
            return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver is missing");
        }

        connection = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?useUnicode=true&serverTimezone=UTC", username, password);

        if (connection == null) {
            throw new SQLException("Failed to connect");
        }
    }

    @Override
    public void close() throws Exception {
        
        if (connection == null)
            return;

        connection.close();
        connection = null;        
    }
}
