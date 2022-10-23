package com.cookos.tasks;

import java.sql.*;
import com.cookos.*;

public class OrderAuthors {
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5")) {

            var statement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = statement.executeQuery("select * from %s order by firstName, lastName".formatted(TableNames.authors));

            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
