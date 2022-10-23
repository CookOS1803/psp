package com.cookos.tasks;

import java.sql.*;
import java.util.*;
import com.cookos.*;

public class AddAuthorISBN {
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5"); var in = new Scanner(System.in)) {

            var selectStatement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = selectStatement.executeQuery("select * from " + TableNames.authors);
            App.printQuery(result);
            result = selectStatement.executeQuery("select * from " + TableNames.titles);
            App.printQuery(result);

            System.out.print("Введите isbn: ");
            var isbn = in.nextLine();
            System.out.print("Введите имя автора: ");
            var firstName = in.nextLine();
            System.out.print("Введите фамилию автора: ");
            var lastName = in.nextLine();


            var addStatement = jdbc.getConnection().createStatement();
            var update = """
            insert into %s values (
                (
                    select authorID from authors where firstName='%s' and lastName='%s'
                ), %s
            )
            """.formatted(TableNames.isbn, firstName, lastName, isbn);
            addStatement.executeUpdate(update);

            result = selectStatement.executeQuery("select * from " + TableNames.isbn);
            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
