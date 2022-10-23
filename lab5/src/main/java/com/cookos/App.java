package com.cookos;

import java.sql.*;
import java.util.*;

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

    public static void printQuery(ResultSet result) throws SQLException {

        var meta = result.getMetaData();
        int columnCount = meta.getColumnCount();
        var columnLengths = new int[columnCount];

        for (int i = 1; i <= columnCount; i++) {
            columnLengths[i - 1] = meta.getColumnLabel(i).length() + 2;
        }   

        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {    
                var obj = result.getObject(i);
                
                int length = obj == null ? 6 : obj.toString().length() + 2;

                if (length > columnLengths[i - 1])
                    columnLengths[i - 1] = length;
            }
        }
        result.absolute(0);

        int totalLength = Arrays.stream(columnLengths).sum();
        
        printSeparators(totalLength);

        for (int i = 1; i <= columnCount; i++) {
            System.out.format("%-" + columnLengths[i - 1] + "s", meta.getColumnLabel(i));
        }        
        System.out.println();
        
        printSeparators(totalLength);

        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {                
                System.out.format("%-" + columnLengths[i - 1] + "s", result.getObject(i));
            }
            System.out.println();
        }
    }    

    private static void printSeparators(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public static int GetInt(Scanner in, String output)
    {
        int result;

        while (true)
        {
            System.out.print(output);

            try
            {
                result = in.nextInt();
                in.nextLine();

                return result;
                
            }
            catch (Exception e)
            {
                in.nextLine();
            }
        }
    }

    public static float GetFloat(Scanner in, String output)
    {
        float result;

        while (true)
        {
            System.out.print(output);

            try
            {
                result = in.nextFloat();
                in.nextLine();

                return result;
                
            }
            catch (Exception e)
            {
                in.nextLine();
            }
        }
    }
}
