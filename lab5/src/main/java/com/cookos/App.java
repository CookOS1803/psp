package com.cookos;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("vsyo ok");
        } catch (ClassNotFoundException e) {
            System.out.println("DDAD");
        }

        var connection = DriverManager.getConnection("jdbc:mysql://localhost/world?useUnicode=true&serverTimezone=UTC", "root", "12345678");
        var s = connection.createStatement();

        var result = s.executeQuery("select * from world.city limit 100;");

        while (result.next()) {
            
            System.out.println(result.getString(2));
        }

    }
}
