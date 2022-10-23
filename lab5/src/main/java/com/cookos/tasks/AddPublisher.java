package com.cookos.tasks;

import java.sql.*;
import java.util.*;
import com.cookos.*;

public class AddPublisher {
    
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5")) {

            String input = null;

            try (var in = new Scanner(System.in)) {
                System.out.println("Введите имя нового издателя: ");
                input = in.nextLine();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            var addStatement = jdbc.getConnection().createStatement();
            addStatement.executeUpdate("insert into %s values (null, '%s')".formatted(TableNames.publishers, input));

            var selectStatement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = selectStatement.executeQuery("select * from " + TableNames.publishers);
            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
