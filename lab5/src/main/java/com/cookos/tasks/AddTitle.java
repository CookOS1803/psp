package com.cookos.tasks;

import java.sql.*;
import java.text.*;
import java.util.*;
import com.cookos.*;

public class AddTitle {
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5"); var in = new Scanner(System.in)) {

            var selectStatement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = selectStatement.executeQuery("select * from " + TableNames.publishers);
            App.printQuery(result);

            System.out.print("Введите isbn: ");
            var isbn = in.nextLine();
            System.out.print("Введите название книги: ");
            var title = in.nextLine();
            int edition = App.GetInt(in, "Введите номер издания: ");
            System.out.print("Введите год: ");
            var year = in.nextLine();
            System.out.print("Введите имя издателя: ");
            var publisher = in.nextLine();
            float price = App.GetFloat(in, "Введите цену: ");

            var symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');

            var format = new DecimalFormat("###.##", symbols);


            var addStatement = jdbc.getConnection().createStatement();
            var update = """
            insert into %s values (
                '%s', '%s', %d, '%s', (
                    select publisherID from publishers where publisherName='%s'
                ), %s
            )
            """.formatted(TableNames.titles, isbn, title, edition, year, publisher, format.format(price));
            addStatement.executeUpdate(update);

            result = selectStatement.executeQuery("select * from " + TableNames.titles);
            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
