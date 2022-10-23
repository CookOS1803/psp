package com.cookos.tasks;

import java.sql.*;
import java.util.*;
import com.cookos.*;

public class AddAuthor {
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5"); var in = new Scanner(System.in)) {

            System.out.print("Введите имя нового автора: ");
            var firstName = in.nextLine();
            System.out.print("Введите фамилию нового автора: ");
            var lastName = in.nextLine();

            var addStatement = jdbc.getConnection().createStatement();
            addStatement.executeUpdate("insert into %s values (null, '%s', '%s')".formatted(TableNames.authors, firstName, lastName));

            var selectStatement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = selectStatement.executeQuery("select * from " + TableNames.authors);
            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
