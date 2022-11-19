package com.cookos.dao;

import java.sql.*;

import com.cookos.model.User;
import com.cookos.util.ConnectorDB;

public class UserDao {
    private static final String SQL_GET_USER = "select login,passw from users where login=? and passw=?";
    private static final String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO users(login ,passw) VALUES (? , ?)";

    private Connection connection;

    public UserDao() {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidUser(final String login, final String password) {

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USER);

            ps.setString(1, login);
            ps.setString(2, password);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return false;
    }

    public boolean insertUser(User user) {
        try {
            var preparedStatement = connection.prepareStatement(SQL_CHECK_LOGIN);
            
            preparedStatement.setString(1, user.getLogin());
            var result = preparedStatement.executeQuery();

            if (result.next()) {
                preparedStatement.close();
                return false;
            }
            else {
                preparedStatement = connection.prepareStatement(SQL_INSERT_USER);    
                preparedStatement.setString(1, user.getLogin()); preparedStatement.setString(2, user.getPassw());    
                preparedStatement.executeUpdate(); preparedStatement.close();
    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }   

}
