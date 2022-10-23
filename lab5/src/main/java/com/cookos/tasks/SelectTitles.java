package com.cookos.tasks;

import java.sql.*;
import java.util.*;
import com.cookos.*;

public class SelectTitles {
    public static void main( String[] args ) throws SQLException {
        try (var jdbc = new JDBC("root", "12345678", "lab5"); var in = new Scanner(System.in)) {

            var selectStatement = jdbc.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            var result = selectStatement.executeQuery("select * from " + TableNames.publishers);
            App.printQuery(result);

            int id = App.GetInt(in, "Введите id издателя: ");

            result = selectStatement.executeQuery("select * from %s where publisherID=%d order by title".formatted(TableNames.titles, id));
            App.printQuery(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
