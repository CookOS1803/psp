package com.cookos.tasks;

import java.sql.*;
import java.util.*;
import com.cookos.*;

public class UpdateAuthor {
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5"); var in = new Scanner(System.in)) {

            var selectStatement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = selectStatement.executeQuery("select * from " + TableNames.authors);
            App.printQuery(result);

            int id = App.GetInt(in, "Введите id автора: ");
            System.out.print("Введите новое имя: ");
            var firstName = in.nextLine();
            System.out.print("Введите новую фамилию: ");
            var lastName = in.nextLine();

            var updateStatement = jdbc.getConnection().createStatement();
            updateStatement.executeUpdate("""
            update %s
            set firstName='%s', lastName='%s'
            where authorID=%d""".formatted(TableNames.authors, firstName, lastName, id));

            result = selectStatement.executeQuery("select * from " + TableNames.authors);
            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
