package com.cookos;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        

        try (var jdbc = new JDBC("root", "12345678", "lab5")) {
            
            var s = jdbc.getConnection().createStatement();

            var result = s.executeQuery("select * from lab5.authors limit 100;");
            while (result.next()) {
    
                System.out.println(result.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
