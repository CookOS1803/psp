package com.cookos.dao;

import java.sql.*;
import com.cookos.util.ConnectorDB;

public class BaseDao implements AutoCloseable {

    protected Connection connection;
    
    public BaseDao() {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
}
