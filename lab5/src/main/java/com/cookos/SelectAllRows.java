package com.cookos;

import java.sql.*;

public class SelectAllRows {

    public static void main( String[] args ) throws SQLException {
        
        try (var jdbc = new JDBC("root", "12345678", "lab5")) {

            var statement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            App.printQuery(statement.executeQuery("select * from " + TableNames.authors));
            App.printQuery(statement.executeQuery("select * from " + TableNames.isbn));
            App.printQuery(statement.executeQuery("select * from " + TableNames.publishers));
            App.printQuery(statement.executeQuery("select * from " + TableNames.titles));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
